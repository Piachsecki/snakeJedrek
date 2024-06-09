package com.platformy_programistyczne.Model;

public class Player extends DefaultSnake{
    public Player() {
        this.dots = 3;
        for (int i = 0; i < dots; ++i) {
            x[i] = 50 - i * 10;
            y[i] = 50;
        }
    }

}
