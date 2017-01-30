/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.policy;

import com.acme.etl.DAO.LoaderUserFromFileCallback;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.SwingUtilities;
import org.apache.log4j.Logger;

/**
 * Обработчик политики вызова.
 * @author Носов А.В.
 */
public class EDTInvocationHandler implements InvocationHandler {

    // Variables declaration
    private static final Logger log = Logger.getLogger(EDTInvocationHandler.class);
    // End of variables declaration

    /**
     * Резкльтат вызова метода.
     */
    private Object invocationResult = null;

    /**
     *
     */
    private LoaderUserFromFileCallback ui;

    /**
     * Создание обработчика.
     * @param ui вызывающий объект
     */
    public EDTInvocationHandler(LoaderUserFromFileCallback ui) {
        this.ui = ui;
    }

    /**
     * Invokes method on target object. If {@link RequiresEDT} annotation present,
     * method is invoked in the EDT thread, otherwise - in current thread.
     *
     * @param proxy  proxy object
     * @param method method to invoke
     * @param args   method arguments
     * @return invocation result
     * @throws Throwable if error occures while calling method
     */
    @Override
    public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
        RequiresEDT mark = method.getAnnotation(RequiresEDT.class);
        if (mark != null) {
            if (SwingUtilities.isEventDispatchThread()) {
                invocationResult = method.invoke(ui, args);
            } else {
                Runnable shell = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            invocationResult = method.invoke(ui, args);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                };
                if (RequiresEDTPolicy.ASYNC.equals(mark.value())) {
                    SwingUtilities.invokeLater(shell);
                } else {
                    SwingUtilities.invokeAndWait(shell);
                }
            }
        } else {
            invocationResult = method.invoke(ui, args);
        }
        return invocationResult;
    }

}
