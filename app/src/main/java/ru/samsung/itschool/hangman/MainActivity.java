package ru.samsung.itschool.hangman;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playBtn = (Button)findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.playBtn) {
            Intent playIntent = new Intent(this, SecondActivity.class);
            this.startActivity(playIntent);
        }
    }
}