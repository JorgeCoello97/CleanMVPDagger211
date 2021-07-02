package com.example.domain.interactor;

import com.example.domain.repositorio.IDictionaryRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

public class ClearDictionaryUseCase extends UseCaseBase<Boolean, Void>{
    private IDictionaryRepository iDictionaryRepository;
    private Scheduler schedulerThread;

    @Inject
    public ClearDictionaryUseCase(IDictionaryRepository iDictionaryRepository, Scheduler schedulerThread){
        this.iDictionaryRepository = iDictionaryRepository;
        this.schedulerThread = schedulerThread;
    }

    @Override
    public Observable<Boolean> implementUseCase(DisposableObserver<Boolean> observer, Void object) {
        Observable<Boolean> dictionaryObservable = iDictionaryRepository.ClearDictionary();
        this.createUseCase(dictionaryObservable,observer,schedulerThread);
        return dictionaryObservable;
    }
}
