package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private Square[][] squares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.GridLayout);
        squares = new Square[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boolean isBlack = Math.random() < 0.5; // Randomly assign black or white
                squares[i][j] = new Square(isBlack);
                addSquareToGrid(i, j, isBlack);
            }
        }
    }

    private void addSquareToGrid(int row, int col, boolean isBlack) {
        Square square = squares[row][col];
        TextView squareView = new TextView(this);
        int squareSize = 350;
        squareView.setWidth(squareSize);
        squareView.setHeight(squareSize);
        squareView.setBackgroundColor(isBlack ? Color.BLACK : Color.WHITE);
        squareView.setOnClickListener(v -> {
            handleSquareClick(row, col);
        });
        gridLayout.addView(squareView);
    }

    private boolean checkWin() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!squares[i][j].isBlack()) {
                    return false; // If any square is not black, the game continues
                }
            }
        }
        return true; // All squares are black, win the game
    }

    private void gameWin() {
        gridLayout.setBackgroundColor(Color.GREEN);
        Toast.makeText(this, "You've won!", Toast.LENGTH_SHORT).show();
    }

    private void handleSquareClick(int row, int col) {
        squares[row][col].toggleColor();
        View squareView = gridLayout.getChildAt(row * 5 + col);
        squareView.setBackgroundColor(squares[row][col].isBlack() ? Color.BLACK : Color.WHITE);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            if (newRow >= 0 && newRow < 5 && newCol >= 0 && newCol < 5) {
                squares[newRow][newCol].toggleColor();
                View adjacentSquareView = gridLayout.getChildAt(newRow * 5 + newCol);
                adjacentSquareView.setBackgroundColor(squares[newRow][newCol].isBlack() ? Color.BLACK : Color.WHITE);
            }
        }

        if (checkWin()) {
            gameWin();
        }
    }

    public void resetPuzzle(View view) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boolean isBlack = Math.random() < 0.5;
                squares[i][j] = new Square(isBlack);
                View squareView = gridLayout.getChildAt(i * 5 + j);
                squareView.setBackgroundColor(isBlack ? Color.BLACK : Color.WHITE);
            }
        }
    }
}
