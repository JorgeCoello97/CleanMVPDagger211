package com.example.domain.interactor;

import com.example.domain.model.Word;
import com.example.domain.repositorio.IDictionaryRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

public class SaveTranslationUseCase extends UseCaseBase<ArrayList<Word>, Word> {
    private IDictionaryRepository iDictionaryRepository;
    private Scheduler schedulerThread;

    @Inject
    public SaveTranslationUseCase(IDictionaryRepository iDictionaryRepository, Scheduler schedulerThread){
        this.iDictionaryRepository = iDictionaryRepository;
        this.schedulerThread = schedulerThread;
    }

    @Override
    public Observable<ArrayList<Word>> implementUseCase(DisposableObserver<ArrayList<Word>> observer, Word word) {
        Observable<ArrayList<Word>> dictionaryObservable = iDictionaryRepository.SaveTranslation(word);
        this.createUseCase(dictionaryObservable, observer, schedulerThread);
        return dictionaryObservable;
    }
}
