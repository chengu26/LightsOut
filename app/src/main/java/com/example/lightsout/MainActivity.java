package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * author: Malissa Chen
 * date: Oct. 7, 2023
 *
 *
 * called when the activity is first created
 * initializes game grid, sets up random square colors
 * adds OnClickListener to the reset button
 */
public class MainActivity extends AppCompatActivity{
    private GridLayout gridLayout;
    private Square[][] squares;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.GridLayout);
        squares = new Square[5][5];

        //initialize the grid with random black & white squares
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                //randomly assign black or white
                boolean isBlack = Math.random() < 0.5;
                squares[i][j] = new Square(isBlack);
                addSquare(i, j, isBlack);
            }
        }

        Button resetButton = findViewById(R.id.resetButton);
        //set OnClickListener for reset button
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetPuzzle(v);
            }
        });
    }

    /**
     * add square to the GridLayout
     *
     * @param row     the row index of the square
     * @param col     the column index of the square
     * @param isBlack indicates whether the square should be black
     */
    private void addSquare(int row, int col, boolean isBlack){
        Square square = squares[row][col];
        TextView squareView = new TextView(this);
        int squareSize = 350;
        squareView.setWidth(squareSize);
        squareView.setHeight(squareSize);
        squareView.setBackgroundColor(isBlack ? Color.BLACK : Color.WHITE);
        squareView.setOnClickListener(v -> {
            squareClick(row, col);
        });
        gridLayout.addView(squareView);
    }

    /**
     * check if the player has won if all squares are black
     *
     * @return true if all squares on the grid are black, indicating a win; false otherwise
    */
    private boolean checkWin(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(!squares[i][j].isBlack()){
                    return false; //if any square is not black, the game continues
                }
            }
        }
        return true; //all squares are black, win the game
    }

    //display win message & set the background color to green.
    private void gameWin(){
        gridLayout.setBackgroundColor(Color.GREEN);
        Toast.makeText(this, "You've won!", Toast.LENGTH_SHORT).show();
    }

    /**
     * handles the click event when a square on the grid is clicked
     * toggles the color of the clicked square & adjacent squares
     * checks for win condition
     *
     * @param row the row of clicked square
     * @param col the column of clicked square
     */
    private void squareClick(int row, int col){
        squares[row][col].toggleColor();
        View squareView = gridLayout.getChildAt(row * 5 + col);
        squareView.setBackgroundColor(squares[row][col].isBlack() ? Color.BLACK : Color.WHITE);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for(int i = 0; i < 4; i++){
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            if (newRow >= 0 && newRow < 5 && newCol >= 0 && newCol < 5){
                squares[newRow][newCol].toggleColor();
                View adjacentSquareView = gridLayout.getChildAt(newRow * 5 + newCol);
                adjacentSquareView.setBackgroundColor(squares[newRow][newCol].isBlack() ? Color.BLACK : Color.WHITE);
            }
        }
        //check win condition after every square click
        if(checkWin()){
            gameWin();
        }
    }

    /**
     * resets the game by generating new random grid
     * sets each square to a new random color (black or white) & update grid.
     *clears the green win screen if the player had won
     *
     * @param view the View object that triggers reset action
     */
    public void resetPuzzle(View view){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                boolean isBlack = Math.random() < 0.5;
                squares[i][j] = new Square(isBlack);
                View squareView = gridLayout.getChildAt(i * 5 + j);
                squareView.setBackgroundColor(isBlack ? Color.BLACK : Color.WHITE);
            }
        } //to get rid of green win screen, if won
        gridLayout.setBackgroundColor(Color.TRANSPARENT);
    }
}
