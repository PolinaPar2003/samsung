package ru.samsung.itschool.hangman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class Bukva extends BaseAdapter {
    private String[] alphabet;
    private LayoutInflater alphabetInf;

    public Bukva(Context c) {
        alphabet = new String[33];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = "" + (char)(i+'А');
        }

        alphabetInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return alphabet.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button letterBtn;
        if (convertView == null) {
            letterBtn = (Button)alphabetInf.inflate(R.layout.bukva, parent, false);
        } else {
            letterBtn = (Button) convertView;
        }
        letterBtn.setText(alphabet[position]);
        return letterBtn;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }
}
