package com.platformy_programistyczne.Model;

import com.platformy_programistyczne.Frame.GameBoard;

public class Computer extends DefaultSnake{
    private boolean stopped;

    public Computer() {
        init();
    }

    public void init() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * GameBoard.POINT_SIZE;
            y[i] = 50;
        }
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        stopped = false;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public void move() {
        if (!stopped) {
            for (int i = dots; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            if (leftDirection) {
                x[0] -= GameBoard.POINT_SIZE;
            }

            if (rightDirection) {
                x[0] += GameBoard.POINT_SIZE;
            }

            if (upDirection) {
                y[0] -= GameBoard.POINT_SIZE;
            }

            if (downDirection) {
                y[0] += GameBoard.POINT_SIZE;
            }
        }
    }

    public void updateDirection(int fruitX, int fruitY) {
        if (x[0] < fruitX) {
            rightDirection = true;
            leftDirection = false;
            upDirection = false;
            downDirection = false;
        } else if (x[0] > fruitX) {
            leftDirection = true;
            rightDirection = false;
            upDirection = false;
            downDirection = false;
        } else if (y[0] < fruitY) {
            downDirection = true;
            upDirection = false;
            leftDirection = false;
            rightDirection = false;
        } else if (y[0] > fruitY) {
            upDirection = true;
            downDirection = false;
            leftDirection = false;
            rightDirection = false;
        }
    }

    public void reverseDirection() {
        if (leftDirection) {
            leftDirection = false;
            rightDirection = true;
        } else if (rightDirection) {
            rightDirection = false;
            leftDirection = true;
        } else if (upDirection) {
            upDirection = false;
            downDirection = true;
        } else if (downDirection) {
            downDirection = false;
            upDirection = true;
        }
    }
    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}

