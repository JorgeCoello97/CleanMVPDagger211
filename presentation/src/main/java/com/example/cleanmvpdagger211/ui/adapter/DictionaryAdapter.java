package com.example.cleanmvpdagger211.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.domain.model.Word;

import java.util.ArrayList;

public class DictionaryAdapter extends ArrayAdapter<Word> {
    ArrayList<Word> words;
    public DictionaryAdapter(@NonNull Context context, @NonNull ArrayList<Word> words) {
        super(context, android.R.layout.simple_list_item_2, android.R.id.text1, words);
        this.words = words;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position,convertView,parent);
        TextView textView1 = view.findViewById(android.R.id.text1);
        TextView textView2 = view.findViewById(android.R.id.text2);

        textView1.setText(words.get(position).getTerm());
        textView2.setText(words.get(position).getTranslation());
        return view;
    }

}
