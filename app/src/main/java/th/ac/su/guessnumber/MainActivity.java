package th.ac.su.guessnumber;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import th.ac.su.guessnumber.model.Answer;

public class MainActivity extends AppCompatActivity {

    Answer a;
    int score =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = new Answer();

        // a.randomValue();

        //Log.i("MainActivity", "ค่าของ answer ที่สุ่มได้คือ"+a.getValue());

        Button guessButton = findViewById(R.id.guess_button);
        // guessButton.setText("Hello");
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText numberEditText = findViewById(R.id.number_edit_text);
                String numText = numberEditText.getText().toString();
                int num = Integer.parseInt(numText);

                Answer.GuessResult result = a.checkAnswer(num);

                TextView resultTextView = findViewById(R.id.result_text_view);

                switch (result) {
                    case OK:
                        score++;
                        Log.i("MainActivity", "คะแนนทั้งหมด: "+ score);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("ผลลัพธ์");
                        dialog.setMessage("ถูกต้องงงงง\n\nคุณต้องการเล่นเกมใหม่หรือไม่");
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //todo: ทำการสุ่มเลขใหม่
                                a = new Answer();
                            }
                        });
                        dialog.setNegativeButton("No", null);
                        dialog.show();

                        resultTextView.setText("");
                        break;
                    case OVER:
                        resultTextView.setText("ผิด มากเกินไป");
                       /* dialog.setMessage("ผิด มากเกินไป");
                        dialog.setPositiveButton("OK", null);*/
                        break;
                    case UNDER:
                        resultTextView.setText("ผิด น้อยเกินไป");
                        /*dialog.setMessage("ผิด น้อยเกินไป");
                        dialog.setPositiveButton("OK", null);*/
                        break;
                }


                //ออกสอบทั้งtoast และ dialog
//                Toast t = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
//                t.show();


            }
        });
        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Exit Guess Number");
                dialog.setMessage("Are you sure you want to exit?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.setNegativeButton("No", null);
                dialog.show();
            }
        });
        Button scoreButton = findViewById(R.id.score_button);
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ScoreActivity.class);
                i.putExtra("score", score);
                startActivity(i);

            }
        });
    }


}