package com.example.data;

import com.example.data.factory.DictionaryDataStoreFactory;
import com.example.domain.model.Word;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import com.example.domain.repositorio.IDictionaryRepository;

@Singleton
public class DictionaryRepositoryImplementation implements IDictionaryRepository {
    DictionaryDataStoreFactory dictionaryDataStoreFactory;

    @Inject
    public DictionaryRepositoryImplementation(DictionaryDataStoreFactory dictionaryDataStoreFactory){
        this.dictionaryDataStoreFactory = dictionaryDataStoreFactory;
    }

    @Override
    public Observable<ArrayList<Word>> GetDictionary() {
        return dictionaryDataStoreFactory.Local().GetDictionary();
    }

    @Override
    public Observable<ArrayList<Word>> SaveTranslation(Word word) {
        return dictionaryDataStoreFactory.Local().SaveTranslation(word);
    }

    @Override
    public Observable<Boolean> ClearDictionary() {
        return dictionaryDataStoreFactory.Local().ClearDictionary();
    }
}
