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
        DTO dto = new DTO();
        m(dto);
        System.out.println(dto.getId());
    }
    
    static void m(DTO arg) {
        arg.setId(1);
    }
}

class DTO {
    private int id = 0;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}