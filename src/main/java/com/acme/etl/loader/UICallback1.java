/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.loader;

import javaparserlog4zhiltsov.policy.RequiresEDT;
import javaparserlog4zhiltsov.policy.RequiresEDTPolicy;

/**
 *
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public interface LoaderUserFromFileCallback {

    /**
     * Устанавливает текстовое сообщение.
     * @param msg сообщение
     */
    @RequiresEDT
    void setMessage(String msg);
    
    /**
     * Размер файла в файтах.
     * @param size размер
     */
    @RequiresEDT
    void setFileSize(long size);
    
    /**
     * Устанавливает кол-во байт в прочитаной строке.
     * @param lineSize кол-во байт
     */
    @RequiresEDT
    void setReadeLienSize(int lineSize);
    
    /**
     * Устанавливает прогресс выполнения чтения файла в байтах.
     * @param progress прогресс
     */
    @RequiresEDT
    void setProgress(long progress);

    /**
     * Запуск потока чтения файла.
     */
    @RequiresEDT
    void startLoading();

    /**
     * Остановка потока чтения файла.
     */
    @RequiresEDT
    void stopLoading();

    /**
     * Ошибки работы потока.
     * @param message сообщение
     */
    @RequiresEDT(RequiresEDTPolicy.SYNC)
    void showError(String message);

}
