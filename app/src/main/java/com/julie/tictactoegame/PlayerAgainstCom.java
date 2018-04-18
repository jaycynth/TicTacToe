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

public class PlayerAgainstCom extends AppCompatActivity {
    private BoardView boardView;
    private Board board;
    int scorex = 0;
    int scoreo = 0;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_against_com);
        boardView = (BoardView) findViewById(R.id.board);
        board = new Board();
        boardView.setBoard(board);
        boardView.setMainActivity(this);

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
    method that shoes the end of the game by displaying a
    popup message showing the scores of the game and setting the scores on the scoreboard
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void gameEnded(char s) {
        String scores = (s == 'T') ? "Game Over. Tie " : "Game Over. " + s + " wins";
        if (s == 'X') {
            scorex = scorex + 1;
            displayPlayerx(scorex);
        }

        if (s == 'O') {
            scoreo = scoreo + 1;
            displayPlayero(scoreo);

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


    public void displayPlayerx(int score) {
        TextView scorex = (TextView) findViewById(R.id.scorex);
        scorex.setText(String.valueOf(score));
    }

    public void displayPlayero(int score) {
        TextView scoreo = (TextView) findViewById(R.id.scoreo);
        scoreo.setText(String.valueOf(score));
    }


    public void reset(View v) {
        scorex = 0;
        scoreo = 0;
        displayPlayerx(scorex);
        displayPlayero(scoreo);
    }

    /*
    method to start a new game
     */
    private void newGame() {
        board.newGame();
        boardView.invalidate();
    }

}
