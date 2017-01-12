/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.edu;

/**
 *
 * @author eugene
 */
public class Main {
    public strictfp static void main(final String... args) {
        m("", "");
    }

    static void m(String... args) { //vararg
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    static void m2(String s, String... aaa) {

    }
}