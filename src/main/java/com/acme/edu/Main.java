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
        //Ariph
        int i = 0;
        System.out.println(  i++ + ++i == 0 );
        i *= 5; 

        System.out.println(1 + 2 + "3" + 4);
        
        //Logic
        System.out.println( true || f() );
        System.out.println( new DTO() == new DTO() ); //same | equal
        
        //Bitwise
        System.out.println(~2);
        System.out.println( 3 >> 1 );
        
        //Ternary
        System.out.println(    
                f() ? "dfdf" : "ffff"
        );
    }

    private static boolean f() {
        return false;
    }
}


class DTO extends Object {
    //.....
}
