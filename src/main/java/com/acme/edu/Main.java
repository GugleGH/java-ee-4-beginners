/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.edu;

import java.io.File;
import java.util.List;

/**
 *
 * @author eugene
 */
public class Main {
    public strictfp static void main(final String[] args) { //IEEE xxx : +,-,*,/
        int[] array = new int[4];
        System.out.println(array.length);
        
        int[] arr = {1,2, 3, 5, 6};
        m( new int[] {} );
        
        int[][] matrix = new int[2][];
        System.out.println(matrix[0]);
        matrix[0] = new int[10];
        matrix[1] = new int[20];
        matrix[0] = new int[] {1,2};
        
        Object[] arra = new Object[9];
        arra[0] = 5; //
        Integer iii = 6; // = new Integeer(6);
        int ii = iii; //iii.intValue()
        
        Integer i1 = 1_00, i2 = 1_00;
        Integer i3 = i1 + i2;
        System.out.println(i3);
        
        System.out.println(i1 == i2);
        
    }
    
    static void m(int[] arg) {
        
    }
}