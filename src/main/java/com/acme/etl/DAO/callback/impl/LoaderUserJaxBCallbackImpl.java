/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.DAO.callback.impl;

import com.acme.etl.core.User;
import com.acme.etl.DAO.LoaderUserFromFileCallback;
import com.acme.etl.DAO.impl.LoaderUserJaxBImpl;
import com.acme.etl.policy.EDTInvocationHandler;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Реализация загрузки пользователей.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class LoaderUserJaxBCallbackImpl implements LoaderUserFromFileCallback {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(LoaderUserJaxBCallbackImpl.class);
    
    private final LoaderUserJaxBImpl loadUsers;
    // End of variables declaration
    
    public LoaderUserJaxBCallbackImpl(String path) {
        LoaderUserFromFileCallback ui = (LoaderUserFromFileCallback) java.lang.reflect.Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{LoaderUserFromFileCallback.class}, new EDTInvocationHandler(this));
        loadUsers = new LoaderUserJaxBImpl(ui, path, 2);
        loadUsers.execute();
    }

    @Override
    public void setMessage(String msg) {
        log.info("Message from runnable: '" + msg + "';");
    }

    @Override
    public void setUser(User user) {
//        log.info("Read user info. id: '" + user.getId() + "'; name: '" + user.getName() + "';");
    }

    @Override
    public void setUsersBatch(List<User> users) {
        log.info("Read user info by batch: '" + users.size() + "';'");
        users.forEach((user) -> {
//            log.info("id: '" + user.getId() + "'; name: '" + user.getName() + "';");
            log.info(user.toString());
        });
    }

    @Override
    public void setFileSize(long size) {
        
    }

    @Override
    public void setReadeLienSize(int lineSize) {
        
    }

    @Override
    public void setProgress(long progress) {
        
    }

    @Override
    public void startLoading() {
        log.info("Start load user from *.xml file.");
    }

    @Override
    public void stopLoading() {
        log.info("Stop load user from *.xml file.");
    }

    @Override
    public void showError(String message) {
        log.fatal(message);
    }
}
