package com.platformy_programistyczne.Model;

import static com.platformy_programistyczne.Frame.GameBoard.*;

public class Frog {
    private int frogX, frogY;

    public void moveFrog() {
        int r = 1 - (int) (Math.random() * 3);
        int moveX = r * POINT_SIZE;

        if ((frogX + moveX) < FIELD_WIDTH && (frogX + moveX) > 0) {
            frogX += moveX;
        }

        r = 1 - (int) (Math.random() * 3);
        int moveY = r * POINT_SIZE;
        if ((frogY + moveY) < FIELD_HEIGHT && (frogY + moveY) > 0) {
            frogY += moveY;
        }
    }

    public int getFrogX() {
        return frogX;
    }

    public void setFrogX(int frogX) {
        this.frogX = frogX;
    }

    public int getFrogY() {
        return frogY;
    }

    public void setFrogY(int frogY) {
        this.frogY = frogY;
    }
}
