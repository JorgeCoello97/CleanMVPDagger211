package com.example.domain.interactor;

import com.example.domain.model.Word;
import com.example.domain.repositorio.IDictionaryRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

public class GetDictionaryUseCase extends UseCaseBase<ArrayList<Word>,Void> {
    private IDictionaryRepository iDictionaryRepository;
    private Scheduler schedulerThread;

    @Inject
    public GetDictionaryUseCase(IDictionaryRepository iDictionaryRepository, Scheduler schedulerThread){
        this.iDictionaryRepository = iDictionaryRepository;
        this.schedulerThread = schedulerThread;
    }

    @Override
    public Observable<ArrayList<Word>> implementUseCase(DisposableObserver<ArrayList<Word>> observer, Void object) {
         Observable<ArrayList<Word>> dictionaryObservable = iDictionaryRepository.GetDictionary();
         this.createUseCase(dictionaryObservable,observer,schedulerThread);
         return dictionaryObservable;
    }
}
