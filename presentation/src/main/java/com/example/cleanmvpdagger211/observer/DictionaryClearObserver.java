package com.example.cleanmvpdagger211.observer;

import android.content.Context;

import com.example.cleanmvpdagger211.R;
import com.example.cleanmvpdagger211.mvp.view.IDictionaryView;

import io.reactivex.observers.DisposableObserver;

public class DictionaryClearObserver extends DisposableObserver<Boolean> {
    private IDictionaryView iDictionaryView;
    private Context context;

    public DictionaryClearObserver (IDictionaryView iDictionaryView, Context context){
        this.iDictionaryView = iDictionaryView;
        this.context = context;
    }


    @Override
    public void onNext(Boolean value) {
        iDictionaryView.dictionaryCleared(value);
    }

    @Override
    public void onError(Throwable e) {
        iDictionaryView.hideLoading();
        iDictionaryView.showMessage(context.getString(R.string.error_not_cleared));
    }

    @Override
    public void onComplete() {
        iDictionaryView.hideLoading();
    }
}
