package com.example.lightsout;

/**
 * author: Malissa Chen
 * date: Oct. 7, 2023
 *
 * this class represents squares in the game.
 */
public class Square {
    private boolean isBlack;

    /**
     * constructor for Square class
     * @param isBlack true if the square is initially black, false if white
     */
    public Square(boolean isBlack) {
        this.isBlack = isBlack;
    }

    /**
     * checks the color of the square
     * @return true if square is black, false if white
     */
    public boolean isBlack() {
        return isBlack;
    }

    /**
     * toggle color of square between black & white
     */
    public void toggleColor() {
        isBlack = !isBlack;
    }
}
