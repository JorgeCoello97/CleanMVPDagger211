package com.example.domain.interactor;

import io.reactivex.observers.DisposableObserver;

public interface IObservableUseCase {
    void suscribe(DisposableObserver observer);
    void cancelSubscription();
}
