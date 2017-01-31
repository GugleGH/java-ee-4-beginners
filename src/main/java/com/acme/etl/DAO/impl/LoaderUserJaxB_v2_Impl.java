/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.DAO.impl;

import com.acme.etl.DAO.LoaderUserFromFile;
import com.acme.etl.DAO.LoaderUserFromFileCallback;
import com.acme.etl.core.User;
import com.acme.etl.core.Users;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

/**
 * Чтение файла *.xml.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class LoaderUserJaxB_v2_Impl implements Runnable, LoaderUserFromFile {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(LoaderUserJaxB_v2_Impl.class);
    
    /** Состояние потока. */
    private boolean executed = false;
    /** Остановка потока. */
    private boolean canceled = false;
    /** Интерфейс работы с UI. */
    protected LoaderUserFromFileCallback ui;
    
    /** Список пользователей. */
    private List users;
    /** Размер партии. */
    private int batch = 5;
    
    private String full_xml;
    
    /** Путь к файлу. */
    private final String path_in;
    // End of variables declaration
    
    /**
     * Чтение *.xml файла с пользователями.
     * @param ui слушатель
     * @param path_in путь к файлу
     */
    public LoaderUserJaxB_v2_Impl(LoaderUserFromFileCallback ui, String path_in) {
        this(ui, path_in, 5);
    }
    
    /**
     * Чтение *.xml файла с пользователями партиями.
     * @param ui слушатель
     * @param path_in путь к файлу
     * @param batch размер партии
     */
    public LoaderUserJaxB_v2_Impl(LoaderUserFromFileCallback ui, String path_in, int batch) {
        this.ui = ui;
        this.path_in = path_in;
        this.batch = batch;
        this.users = new ArrayList();
    }

    @Override
    public void run() {
        ui.startLoading();
        ui.setMessage("Loader Users from JaxB v2.");
        if (path_in == null || path_in.isEmpty()) {
            ui.showError("Пустой путь к файлу.");
            return;
        }
        
        //
        full_xml = "";
        Path path = Paths.get(path_in);
        try (Scanner sc = new Scanner(path, StandardCharsets.UTF_8.name()) ) {
            
            ui.setFileSize(new File(path_in).length());
            long full = 0;
            ui.setMessage(path.getFileName().toString());
            
            while (sc.hasNextLine()) {
                if (isCanceled())
                    break;
                
                String line = sc.nextLine();
                full += line.length() + 1;
                full_xml += line;
                
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    
                    StringReader reader = new StringReader(full_xml);
                    User us = (User) jaxbUnmarshaller.unmarshal( reader );
                    
                    ui.setMessage(full_xml);
                    full_xml = "";
                    users.add(us);
                    if (users.size() == batch) {
                        sendBatch();
                    }
//                    ui.setUsersBatch(us.getUsers());

                } catch (JAXBException ex) {
                    ui.showError("V2: " + line);
                }
                
            }
            
            ui.setMessage(full_xml);
            ui.setProgress(full);
        } catch (IOException ex) {
            ui.showError("Ошибка чтения файла: " + ex.getMessage());
        } finally {
            ui.stopLoading();
        }
    }
    
    private void sendBatch() {
        ui.setUsersBatch(users);
        users = new ArrayList();
    }

    @Override
    public synchronized void execute() {
        if (executed) {
            throw new IllegalStateException("Поток чения файла запущен.");
        }
        executed = true;
        Thread t = new Thread(this, "Запуск потока чтения файла.");
        t.start();
    }

    @Override
    public synchronized void cancel() {
        canceled = true;
    }

    /**
     * Возвращает состояние потока.
     * @return <b>true</b> - поток запущен, <br>
     * <b>false</b>
     */
    private synchronized boolean isCanceled() {
        return canceled;
    }

}
