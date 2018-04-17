package com.julie.tictactoegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button multi, solo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solo = (Button) findViewById(R.id.solo);
        multi = (Button) findViewById(R.id.multi);

        //sends the player to play against another player
        multi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent multiIntent = new Intent(view.getContext(), PlayerAgainstPlayer.class);
                if (multiIntent.resolveActivity(getPackageManager()) != null) {
                    view.getContext().startActivity(multiIntent);
                }

            }
        });

        //this sends the player to a play against the computer
        solo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent soloIntent = new Intent(view.getContext(), PlayerAgainstCom.class);
                if (soloIntent.resolveActivity(getPackageManager()) != null) {
                    view.getContext().startActivity(soloIntent);
                }

            }
        });
    }
}
