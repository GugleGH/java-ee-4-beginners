/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.DAO.impl;

import com.acme.etl.DAO.LoaderUserFromFile;
import com.acme.etl.DAO.LoaderUserFromFileCallback;
import com.acme.etl.core.Users;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

/**
 * Чтение файла *.xml.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class LoaderUserJaxBImpl implements Runnable, LoaderUserFromFile {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(LoaderUserJaxBImpl.class);
    
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
    
    /** Путь к файлу. */
    private final String path_in;
    // End of variables declaration
    
    /**
     * Чтение *.xml файла с пользователями.
     * @param ui слушатель
     * @param path_in путь к файлу
     */
    public LoaderUserJaxBImpl(LoaderUserFromFileCallback ui, String path_in) {
        this(ui, path_in, 5);
    }
    
    /**
     * Чтение *.xml файла с пользователями партиями.
     * @param ui слушатель
     * @param path_in путь к файлу
     * @param batch размер партии
     */
    public LoaderUserJaxBImpl(LoaderUserFromFileCallback ui, String path_in, int batch) {
        this.ui = ui;
        this.path_in = path_in;
        this.batch = batch;
        this.users = new ArrayList();
    }

    @Override
    public void run() {
        ui.startLoading();
        ui.setMessage("Loader Users from JaxB.");
        if (path_in == null || path_in.isEmpty()) {
            ui.showError("Пустой путь к файлу.");
            return;
        }
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //We had written this file in marshalling example
            Users us = (Users) jaxbUnmarshaller.unmarshal( new File(path_in) );
            users = us.getUsers();
            
            ui.setUsersBatch(users);
            
        } catch (JAXBException ex) {
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
