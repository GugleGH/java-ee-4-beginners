/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.policy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Анотации для методов в потоке.
 * @author Носов А.В.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresEDT {

    /**
     * Политика выполнения методов в потоке.
     * @return тип выполнения метода
     */
    RequiresEDTPolicy value() default RequiresEDTPolicy.ASYNC;

}
