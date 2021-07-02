package com.example.cleanmvpdagger211.mvp.view;

import com.example.domain.model.Word;

import java.util.ArrayList;

public interface IDictionaryView extends IDataView{
    void renderData(ArrayList<Word> dictionary);
    void dictionaryCleared(Boolean value);
}
