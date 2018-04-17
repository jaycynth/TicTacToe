package com.julie.tictactoegame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayerAgainstPlayer extends AppCompatActivity {
    private BoardViewTwo boardViewTwo;
    private BoardTwo boardTwo;
    Button reset;
    int scorex = 0;
    int scoreo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_against_player);

        boardViewTwo = (BoardViewTwo) findViewById(R.id.boardTwo);
        boardTwo = new BoardTwo();
        boardViewTwo.setBoard(boardTwo);
        boardViewTwo.setMainActivity(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_game) {
            newGame();
        }

        return super.onOptionsItemSelected(item);
    }

    /*
   method to display the score of player 1
    */
    public void displayPlayerx(int score) {
        TextView scorex = (TextView) findViewById(R.id.scorex);
        scorex.setText(String.valueOf(score));
    }

    /*
    method to display the score of player 2
     */
    public void displayPlayero(int score) {
        TextView scoreo = (TextView) findViewById(R.id.scoreo);
        scoreo.setText(String.valueOf(score));
    }

    /*
    popup message showing the scores of the game
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void gameEnded(char c) {
        String scores = (c == 'T') ? "Game Ended. Tie " : "GameEnded. " + c + " wins";
        if (c == 'X') {
            scorex = scorex + 1;
            displayPlayerx(scorex);
        } else {
            scoreo = scoreo + 1;
            displayPlayerx(scoreo);
        }


        new AlertDialog.Builder(this).setTitle("SCORE:").
                setMessage(scores).
                setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        newGame();
                    }
                }).show();
    }


    /*
    method to start a new game

     */
    private void newGame() {
        boardTwo.newGame();
        boardViewTwo.invalidate();
    }

    /*
    method to reset the score board
     */
    public void reset(View v) {
        scorex = 0;
        scoreo = 0;
        displayPlayerx(scorex);
        displayPlayero(scoreo);
    }

}

