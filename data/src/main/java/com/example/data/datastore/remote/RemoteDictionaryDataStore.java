package com.example.data.datastore.remote;

import android.content.Context;

import com.example.data.datastore.IDictionaryDataStore;
import com.example.domain.model.Word;
import com.example.domain.repositorio.IDictionaryRepository;

import java.util.ArrayList;

import io.reactivex.Observable;

public class RemoteDictionaryDataStore implements IDictionaryDataStore {
    private Context context;

    public RemoteDictionaryDataStore(Context context){
        this.context = context;
    }
    @Override
    public Observable<ArrayList<Word>> GetDictionary() {
        return null;
    }

    @Override
    public Observable<ArrayList<Word>> SaveTranslation(Word word) {
        return null;
    }

    @Override
    public Observable<Boolean> ClearDictionary() {
        return null;
    }
}
