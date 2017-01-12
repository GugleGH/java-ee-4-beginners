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
        if (false) {
            System.out.println("fff");
        } else if(false) {
            
        }
        
        int i = 0;
        switch(i) {
            case 1: {
                System.out.println("1"); 
            } break;
            case 2: System.out.println("2"); break;
            default: System.out.println("!!!!!"); break;
        }
        
        for (int counter = 0; counter < 10; ++counter, System.out.println(counter)) {
            System.out.println(">> " + counter);
        }
//        for(;;) {}
        
        for (String arg : args) { //-> for ()
            System.out.println(arg);
        }
        
        outeeee_$r: do {
            inneeeeeeeeeer: while (true) {
                if (true) continue outeeee_$r;
            }
        } while(false);   
        
        final int co = 7;
        co = 6;
        
        final DTO dto = new DTO();
        dto = null;

    }
}

final class DTO {
    private final int id;
    public static final int MY_SUPER_CONST;
    
    static {
        MY_SUPER_CONST = 0;
    }
    
    {
        System.out.println("fff");
    }
    
    DTO() {
        super();
        id = 0;
    }
    
    final void m() {
        
    }
}