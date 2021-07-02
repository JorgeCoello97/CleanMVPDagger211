package com.example.cleanmvpdagger211.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cleanmvpdagger211.R;
import com.example.cleanmvpdagger211.mvp.presenter.DictionaryPresenter;
import com.example.cleanmvpdagger211.mvp.view.IDictionaryView;
import com.example.cleanmvpdagger211.ui.adapter.DictionaryAdapter;
import com.example.domain.model.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class DictionaryActivity extends AppCompatActivity implements IDictionaryView, SwipeRefreshLayout.OnRefreshListener {
    Button btn_save_word;
    FloatingActionButton btn_add;
    EditText term_edit_text;
    EditText translation_edit_text;
    ListView list_view_dictionary;
    LinearLayout linear_layout_word_edition;
    ProgressBar loading_progress;
    SwipeRefreshLayout swipe_layout;
    @Inject
    DictionaryPresenter dictionaryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        loading_progress  = findViewById(R.id.loading_progress);
        swipe_layout = findViewById(R.id.swipe_layout);
        swipe_layout.setOnRefreshListener(this);
        dictionaryPresenter.initialize();
        term_edit_text = findViewById(R.id.term_edit_text);
        translation_edit_text = findViewById(R.id.translation_edit_text);
        btn_save_word = findViewById(R.id.btn_save_word);
        list_view_dictionary = findViewById(R.id.list_view_dictionary);
        linear_layout_word_edition = findViewById(R.id.linear_layout_word_edition);
        btn_add = findViewById(R.id.btn_add);
        btn_save_word.setOnClickListener(saveTranslationClick);
        btn_add.setOnClickListener(buttonAddClick);


    }

    //Evento click para almacenar la palabra traducida.
    View.OnClickListener saveTranslationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dictionaryPresenter.saveTranslation(term_edit_text.getText().toString(),translation_edit_text.getText().toString());
        }
    };
    View.OnClickListener buttonAddClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (linear_layout_word_edition.getVisibility() == View.VISIBLE){
                linear_layout_word_edition.setVisibility(View.GONE);
            }else{
                linear_layout_word_edition.setVisibility(View.VISIBLE);
            }
        }
    };
    public void refreshAdapter(ArrayList<Word> dictionary){
        ArrayAdapter<Word> adapter = new DictionaryAdapter(this, dictionary);
        list_view_dictionary.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        dictionaryPresenter.getDictionary();
    }

    @Override
    public void renderData(ArrayList<Word> dictionary) {
        refreshAdapter(dictionary);
        linear_layout_word_edition.setVisibility(View.GONE);
        term_edit_text.setText("");
        translation_edit_text.setText("");
    }

    @Override
    public void dictionaryCleared(Boolean value) {
        if (value){
            ArrayAdapter<Word> adapter = new DictionaryAdapter(this, new ArrayList<Word>());
            list_view_dictionary.setAdapter(adapter);
        }
    }

    @Override
    public void showLoading() {
        loading_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading_progress.setVisibility(View.INVISIBLE);
        swipe_layout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dictionary,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.clear_dictionary){
            dictionaryPresenter.clearDictionary();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dictionaryPresenter.destroy();
    }
}