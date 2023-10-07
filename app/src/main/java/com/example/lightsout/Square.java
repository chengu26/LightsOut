package com.example.lightsout;

/* author: Malissa Chen
 * date: Oct. 7, 2023
 */

public class Square {
    private boolean isBlack;

    public Square(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void toggleColor() {
        isBlack = !isBlack;
    }
}
