/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.DAO;

/**
 * Работа с файлом.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public interface LoaderUserFromFile {

    /**
     * Запустить поток.
     */
    void execute();

    /**
     * Остановить поток.
     */
    void cancel();

}
