package com.zagazig.scorekeeperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTeamA_1Point , btnTeamA_5Points , btnTeamA_Foul , btnTeamB_1Point
            , btnTeamB_5Points , btnTeamB_Foul , btn_EndGame;
    private TextView teamAScoreNumber, teamAFailNumber, teamBScoreNumber, teamBFailNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize Variables
        initVariable();
    }

    private void initVariable() {
        //initialize  Buttons
        btnTeamA_1Point =  findViewById(R.id.btnTeamA_1Point);
        btnTeamA_5Points =  findViewById(R.id.btnTeamA_5Points);
        btnTeamA_Foul =  findViewById(R.id.btnTeamA_Foul);
        btnTeamB_1Point =  findViewById(R.id.btnTeamB_1Point);
        btnTeamB_5Points =  findViewById(R.id.btnTeamB_5Points);
        btnTeamB_Foul =  findViewById(R.id.btnTeamB_Foul);
        btn_EndGame =  findViewById(R.id.btn_EndGame);

        //initialize TextView
        teamAScoreNumber =  findViewById(R.id.teamAScoreNumber);
        teamAFailNumber =  findViewById(R.id.teamAFailNumber);
        teamBScoreNumber =  findViewById(R.id.teamBScoreNumber);
        teamBFailNumber =  findViewById(R.id.teamBFailNumber);

        // onclick Buttons
        btnTeamA_1Point.setOnClickListener(this);
        btnTeamA_5Points.setOnClickListener(this);
        btnTeamA_Foul.setOnClickListener(this);
        btnTeamB_1Point.setOnClickListener(this);
        btnTeamB_5Points.setOnClickListener(this);
        btnTeamB_Foul.setOnClickListener(this);
        btn_EndGame.setOnClickListener(this);
    }

    // display Button When another player playing ...
    private void displayPlayerRole(Boolean status){
        btnTeamA_1Point.setEnabled(status);
        btnTeamA_5Points.setEnabled(status);
        btnTeamA_Foul.setEnabled(status);
        btnTeamA_1Point.setBackground(getDrawable(R.drawable.btn_player));
        btnTeamA_5Points.setBackground(getDrawable(R.drawable.btn_player));
        btnTeamA_Foul.setBackground(getDrawable(R.drawable.btn_player));
        if (status){
            btnTeamB_1Point.setEnabled(false);
            btnTeamB_5Points.setEnabled(false);
            btnTeamB_Foul.setEnabled(false);
            btnTeamB_1Point.setBackground(getDrawable(R.drawable.btn_unplayer));
            btnTeamB_5Points.setBackground(getDrawable(R.drawable.btn_unplayer));
            btnTeamB_Foul.setBackground(getDrawable(R.drawable.btn_unplayer));
        }else {
            btnTeamB_1Point.setEnabled(true);
            btnTeamB_5Points.setEnabled(true);
            btnTeamB_Foul.setEnabled(true);
            btnTeamB_1Point.setBackground(getDrawable(R.drawable.btn_player));
            btnTeamB_5Points.setBackground(getDrawable(R.drawable.btn_player));
            btnTeamB_Foul.setBackground(getDrawable(R.drawable.btn_player));
            btnTeamA_1Point.setBackground(getDrawable(R.drawable.btn_unplayer));
            btnTeamA_5Points.setBackground(getDrawable(R.drawable.btn_unplayer));
            btnTeamA_Foul.setBackground(getDrawable(R.drawable.btn_unplayer));
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnTeamA_1Point:
                setNewScoreTeamA(1);
                displayPlayerRole(false);
                break;
            case R.id.btnTeamA_5Points:
                setNewScoreTeamA(5);
                displayPlayerRole(false);
                break;
            case R.id.btnTeamA_Foul:
                setNewFailTeamA();
                displayPlayerRole(false);
                break;
            case R.id.btnTeamB_1Point:
                setNewScoreTeamB(1);
                displayPlayerRole(true);
                break;
            case R.id.btnTeamB_5Points:
                setNewScoreTeamB(5);
                displayPlayerRole(true);
                break;
            case R.id.btnTeamB_Foul:
                setNewFailTeamB();
                displayPlayerRole(true);
                break;
            case R.id.btn_EndGame:
                CharSequence valueTeamA = teamAScoreNumber.getText();
                int teamA = Integer.parseInt(valueTeamA.toString());
                CharSequence valueTeamB = teamBScoreNumber.getText();
                int teamB = Integer.parseInt(valueTeamB.toString());

                String winner;
                if (teamA != 0 && teamB != 0){
                    if (teamA > teamB){
                        winner = "Team A" ;
                    }else {
                        winner = "Team B" ;
                    }
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle(getString(R.string.title_dialog));
                    dialog.setMessage(getString(R.string.message_dialog) + winner);
                    dialog.setPositiveButton(getText(R.string.button_dialog), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            teamAScoreNumber.setText("0");
                            teamAFailNumber.setText("0");
                            teamBScoreNumber.setText("0");
                            teamBFailNumber.setText("0");
                            dialog.dismiss();
                        }
                    });
                    AlertDialog builder = dialog.create();
                    builder.show();
                }else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle(getString(R.string.title_dialog_two));
                    dialog.setMessage(getString(R.string.message_dialog_two));
                    dialog.setPositiveButton(getText(R.string.button_dialog), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            teamAScoreNumber.setText("0");
                            teamAFailNumber.setText("0");
                            teamBScoreNumber.setText("0");
                            teamBFailNumber.setText("0");
                            dialog.dismiss();
                        }
                    });
                    AlertDialog builder = dialog.create();
                    builder.show();
                }

                break;
        }
    }

    // method to increase score TeamA
    private void setNewScoreTeamA(int score){
        CharSequence value = teamAScoreNumber.getText();
        int currentScore = Integer.parseInt(value.toString());
        int scoreNumberTeamA = currentScore + score;
        Log.i("CHECK_VALUE", String.valueOf(scoreNumberTeamA));
        teamAScoreNumber.setText(String.valueOf(scoreNumberTeamA));
    }

    // method to increase fail TeamA
    private void setNewFailTeamA(){
        CharSequence value = teamAFailNumber.getText();
        int currentScore = Integer.parseInt(value.toString());
        int failNumberTeamA = currentScore + 1;
        Log.i("CHECK_VALUE", String.valueOf(failNumberTeamA));
        teamAFailNumber.setText(String.valueOf(failNumberTeamA));
    }

    // method to increase score TeamA
    private void setNewScoreTeamB(int score){
        CharSequence value = teamBScoreNumber.getText();
        int currentScore = Integer.parseInt(value.toString());
        int scoreNumberTeamB = currentScore + score;
        Log.i("CHECK_VALUE", String.valueOf(scoreNumberTeamB));
        teamBScoreNumber.setText(String.valueOf(scoreNumberTeamB));
    }

    // method to increase fail TeamA
    private void setNewFailTeamB(){
        CharSequence value = teamBFailNumber.getText();
        int currentScore = Integer.parseInt(value.toString());
        int failNumberTeamB = currentScore + 1;
        Log.i("CHECK_VALUE", String.valueOf(failNumberTeamB));
        teamBFailNumber.setText(String.valueOf(failNumberTeamB));
    }
}
