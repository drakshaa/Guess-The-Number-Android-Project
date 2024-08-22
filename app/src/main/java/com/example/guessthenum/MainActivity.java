package com.example.guessthenum;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4;
    EditText guess;
    Button btn1, btn2;
    int val;
    int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView3);
        txt3 = findViewById(R.id.textView4);
        txt4 = findViewById(R.id.textView2);
        guess = findViewById(R.id.enterTxt);
        btn1 = findViewById(R.id.button2);
        btn2 = findViewById(R.id.button);
        txt3.setText(" Hey! This is Draksha." +
                "\n\n Click 'Start' to play the game. \n Have fun !!");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                val = random.nextInt(100) + 1;
                guess.setText("");
                txt3.setText(" ");
                txt3.setText("Enter your guess.");
                attempts = 0;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuess();
            }
        });
    }

    public void checkGuess() {
        txt3.setText(" ");
        try {
            String userinput = guess.getText().toString();
            attempts++;

            if (!userinput.isEmpty()) {
                int guessnum = Integer.parseInt(userinput);
                if (guessnum == val) {
                    txt4.setText("Your guess is correct!! \n\n click 'start' to play again.");
                } else if (attempts < 5) {
                    guess.setText("");
                    txt4.setText(" Incorrect guess!! \n Try Again. \n\n Attempts left: " + (5 - attempts));
                } else {
                    txt4.setText(" You lost! \n\n Correct answer is " + val + " \n Click 'Start' to play again.");
                }
            } else {
                txt4.setText("Please enter a valid number.");
            }
        } catch (NumberFormatException e) {
            txt4.setText(" Invalid input. \n Please enter a number.");
        }
        }
    }
