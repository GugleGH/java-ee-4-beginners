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
    public strictfp static void main(final String[] args) {
        Colorr color = Colorr.R;
        color.name();

        Colorr.values();
    }
}

enum Colorr {
    R, G, B
}

final class Color {
    public static final Color RED = new Color(1);
    public static final Color GREEN = new Color(2);
    public static final Color BLUE = new Color(3);

    private int ordinal;

    private Color(int ordinal) {
        this.ordinal = ordinal;
    }
}
