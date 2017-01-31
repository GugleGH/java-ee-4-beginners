/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.DAO.impl;

import com.acme.etl.core.User;
import com.acme.etl.DAO.LoaderUserFromFile;
import com.acme.etl.DAO.LoaderUserFromFileCallback;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Чтение файла *.csv.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class LoaderUserCSVImpl implements Runnable, LoaderUserFromFile {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(LoaderUserCSVImpl.class);
    
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
     * Чтение *.csv файла с пользователями.
     * @param ui слушатель
     * @param path_in путь к файлу
     */
    public LoaderUserCSVImpl(LoaderUserFromFileCallback ui, String path_in) {
        this(ui, path_in, 5);
    }
    
    /**
     * Чтение *.csv файла с пользователями партиями.
     * @param ui слушатель
     * @param path_in путь к файлу
     * @param batch размер партии
     */
    public LoaderUserCSVImpl(LoaderUserFromFileCallback ui, String path_in, int batch) {
        this.ui = ui;
        this.path_in = path_in;
        this.batch = batch;
        this.users = new ArrayList();
    }

    @Override
    public void run() {
        ui.startLoading();
        ui.setMessage("Loader Users from CSV.");
        if (path_in == null || path_in.isEmpty()) {
            ui.showError("Пустой путь к файлу.");
            return;
        }
        
        Path path = Paths.get(path_in);
        try (Scanner sc = new Scanner(path, StandardCharsets.UTF_8.name()) ) {
            
            ui.setFileSize(new File(path_in).length());
            long full = 0;
            ui.setMessage(path.getFileName().toString());
            
            int i = 0;
            
            while (sc.hasNextLine()) {
                if (isCanceled())
                    break;
                
                String line = sc.nextLine();
                full += line.length() + 1;
                String[] userParameters = line.split(",");
                User user = new User(Integer.parseInt(userParameters[0]), userParameters[1]);
                ui.setUser(user);
                
                users.add(user);
                if (users.size() == batch) {
                    i = 0;
                    sendBatch();
                }
                
            }
            
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
