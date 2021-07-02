package com.example.domain.repositorio;

import com.example.domain.model.Word;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface IDictionaryRepository {
    Observable<ArrayList<Word>> GetDictionary();
    Observable<ArrayList<Word>> SaveTranslation(Word word);
    Observable<Boolean> ClearDictionary();
}
