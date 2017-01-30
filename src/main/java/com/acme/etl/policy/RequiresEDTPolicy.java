/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.policy;

/**
 * Политика безопастности для вызова методов в потоке.
 * @author Носов А.В.
 */
public enum RequiresEDTPolicy {

    /**
     * Асинхронный вызов.
     */
    ASYNC,

    /**
     * Синхронный вызов.
     */
    SYNC

}
