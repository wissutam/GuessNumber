package th.ac.su.guessnumber.model;

import android.util.Log;

import java.util.Random;


public class Answer {
    public enum GuessResult {
        OVER,
        UNDER,
        OK
    }

    private int value;

    public Answer() {
        randomValue();
    }

    public void randomValue() {
        // code สำหรับสุ่มค่า value
        Random r = new Random();
        this.value = r.nextInt(100);
        Log.i("MainActivity", "ค่าของ value ที่สุ่มได้คือ" + this.value);
    }

  /*  public int getValue() {

        return value;
    }

    public void setValue(int value) {
        this.value = value;
    } */

    public GuessResult checkAnswer(int guess) {
        GuessResult result;

        if (guess == this.value) {
            result = GuessResult.OK;
        } else if (guess > this.value) {
            result = GuessResult.OVER;
        } else {
            result = GuessResult.UNDER;
        }
        return result;

    }
}
