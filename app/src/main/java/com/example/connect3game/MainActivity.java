package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //  0 = yellow & 1 = red & 2 = empty
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    String msg;
    boolean gameActive = true;
    int j; int i;

    public void dropIn (View view) {

         //
        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300).rotation(360);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    if (activePlayer == 1) {
                        msg = "Yellow";

                    } else {
                        msg = "Red";

                    }
                    //Toast.makeText(this,"Someone Won",Toast.LENGTH_LONG).show();

                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(msg + " Has Won!");

                    Button button  = (Button) findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE);


                }
            }

        }

    }

    public void playAgain(View view) {

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        Button button  = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(j);

            counter.setImageDrawable(null);

        }

        for( j=0; j< gameState.length;j++){

            gameState[j] = 2;
        }
         activePlayer = 0;

        gameActive = true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}