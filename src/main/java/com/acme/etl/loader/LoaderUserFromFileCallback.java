/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.loader;

import com.acme.etl.core.User;
import com.acme.etl.policy.RequiresEDT;
import com.acme.etl.policy.RequiresEDTPolicy;
import java.util.List;

/**
 * 
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public interface LoaderUserFromFileCallback {

    /**
     * Устанавливает текстовое сообщение.
     * @param msg сообщение
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void setMessage(String msg);
    
    /**
     * Устанавливает прочитанного пользователя.
     * @param user пользователь
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void setUser(User user);
    
    /**
     * Устнавливает пачку прочитанных пользователей.
     * @param users список пользоватлей
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void setUsersBatch(List<User> users);
    
    /**
     * Размер файла в файтах.
     * @param size размер
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void setFileSize(long size);
    
    /**
     * Устанавливает кол-во байт в прочитаной строке.
     * @param lineSize кол-во байт
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void setReadeLienSize(int lineSize);
    
    /**
     * Устанавливает прогресс выполнения чтения файла в байтах.
     * @param progress прогресс
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void setProgress(long progress);

    /**
     * Запуск потока чтения файла.
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void startLoading();

    /**
     * Остановка потока чтения файла.
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void stopLoading();

    /**
     * Ошибки работы потока.
     * @param message сообщение
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void showError(String message);

}
