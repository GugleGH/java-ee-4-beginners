/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.edu;

import java.io.File;

/**
 *
 * @author eugene
 */
public class Main {
    public strictfp static void main(String[] args) { //IEEE xxx : +,-,*,/
        byte b = 0; //0..255 | -127..128
        short s = 0; //
        int i = 0;
        long l = 9999999999L;
        
        float f = 0.f;
        double façade = 0.;
        
        char ch = 1234;
        boolean bb = true | false;
        
        byte counter = 0;
        for (int ii = 0; ii < 130; ii++) {
            counter++;
        }
        
        System.out.println(counter);
        
        System.out.println(.1f + .2f);
        
    }
}
