package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
    }

    int scoreA = 0;
    int scoreB = 0;
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewA.setText(String.valueOf(scoreA));
    }

    public void increase3(View view) {
        scoreA=scoreA+3;
        displayForTeamA(scoreA);
    }
    public void increase2(View view) {
        scoreA=scoreA+2;
        displayForTeamA(scoreA);
    }
    public void increase1(View view) {
        scoreA=scoreA+1;
        displayForTeamA(scoreA);
    }

    public void displayForTeamB(int score) {
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        scoreViewB.setText(String.valueOf(scoreB));
    }

    public void increase3b(View view) {
        scoreB=scoreB+3;
        displayForTeamB(scoreB);
    }
    public void increase2b(View view) {
        scoreB=scoreB+2;
        displayForTeamB(scoreB);
    }
    public void increase1b(View view) {
        scoreB=scoreB+1;
        displayForTeamB(scoreB);
    }

    public void reset(View view){
        scoreA=0;
        scoreB=0;
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
    }
}
