package ru.samsung.itschool.hangman;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private String[] words;
    private Random random;
    private String now_word;
    private LinearLayout word_layout;
    private TextView[] char_views;

    private GridView alphabet;
    private Bukva bkvAdapt;

    private ImageView[] body_parts;
    private int number_parts = 5;
    private int now_part;
    private int number_bukva;
    private int number_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        random = new Random();
        now_word = "";
        word_layout = (LinearLayout)findViewById(R.id.word);

        alphabet = (GridView)findViewById(R.id.bukva);

        body_parts = new ImageView[number_parts];
        body_parts[0] = (ImageView)findViewById(R.id.first_step);
        body_parts[1] = (ImageView)findViewById(R.id.second_step);
        body_parts[2] = (ImageView)findViewById(R.id.third_step);
        body_parts[3] = (ImageView)findViewById(R.id.fourth_step);
        body_parts[4] = (ImageView)findViewById(R.id.fifth_step);

        play();
    }

    private void play() {
        String new_word = words[random.nextInt(words.length)];
        while(new_word.equals(now_word)) new_word = words[random.nextInt(words.length)];
        now_word = new_word;

        char_views = new TextView[now_word.length()];
        word_layout.removeAllViews();
        for (int i = 0; i < now_word.length(); i++) {
            char_views[i] = new TextView(this);
            char_views[i].setText(""+now_word.charAt(i));
        }
        for (int i = 0; i < now_word.length(); i++) {
            char_views[i] = new TextView(this);
            char_views[i].setText(""+now_word.charAt(i));

            char_views[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            char_views[i].setGravity(Gravity.CENTER);
            char_views[i].setTextColor(Color.parseColor("#7fc7ff"));
            char_views[i].setBackgroundResource(R.drawable.stroka);
            word_layout.addView(char_views[i]);
        }

        bkvAdapt=new Bukva(this);
        alphabet.setAdapter(bkvAdapt);

        now_part=0;
        number_bukva=now_word.length();
        number_right=0;
        for(int i = 0; i < number_parts; i++) {
            body_parts[i].setVisibility(View.INVISIBLE);
        }
    }

    public void bukvaPressed(View view) {
        String bkv = ((Button) view).getText().toString();
        char bkvChar = bkv.charAt(0);
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.bukva_nazh);
        boolean right = false;
        for(int k = 0; k < now_word.length(); k++) {
            if(now_word.charAt(k)==bkvChar){
                right = true;
                number_right++;
                char_views[k].setTextColor(Color.BLACK);
            }
        }

        if (right) {
            if (number_right == number_bukva) {
                disableBtns();

                AlertDialog.Builder win = new AlertDialog.Builder(this);
                win.setTitle("УРА");
                win.setMessage("Вы победили!\n\nОтвет в этом раунде:\n\n"+now_word);
                win.setPositiveButton("Играть снова",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SecondActivity.this.play();
                            }});

                win.setNegativeButton("Выход",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SecondActivity.this.finish();
                            }});

                win.show();
            }
        }
        else if (now_part < number_parts) {
                body_parts[now_part].setVisibility(View.VISIBLE);
                now_part++;
        }
        else{
                disableBtns();

                AlertDialog.Builder lose = new AlertDialog.Builder(this);
                lose.setTitle("НЕЕЕТ");
                lose.setMessage("Вы проиграли!\n\nОтвет в этом раунде:\n\n"+now_word);
                lose.setPositiveButton("Играть снова",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SecondActivity.this.play();
                            }});

                lose.setNegativeButton("Выход",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SecondActivity.this.finish();
                            }});

                lose.show();
            }

            }



    public void disableBtns() {
        int numLetters = alphabet.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            alphabet.getChildAt(l).setEnabled(false);
        }
    }
}
