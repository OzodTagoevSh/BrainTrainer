package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView scoreTextView;
    TextView questionTextView;
    TextView timerTextView;
    CountDownTimer countDownTimer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    int score = 0;
    int numberOfQuestions = 0;
    ArrayList<Integer> answers = new ArrayList<>();

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.resultTextView));
    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText(score + "/" + numberOfQuestions);
        timerTextView.setText("30s");
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        newQuestion();
        countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText((int) millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Done!");
                countDownTimer.cancel();
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        String result = "Wrong (:";
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            result = "Correct!";
            score++;
        }
        numberOfQuestions++;
        resultTextView.setText(result);
        scoreTextView.setText(score + "/" + numberOfQuestions);
        resultTextView.setVisibility(View.VISIBLE);
        newQuestion();
    }

    public void newQuestion() {
        Random random = new Random();
        int a = random.nextInt(20) + 1;
        int b = random.nextInt(20) + 1;

        questionTextView.setText(a + " + " + b);

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        for(int i = 0; i < 4; i++) {
            if(i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(40) + 1;
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(40) + 1;
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);

        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
    }
}