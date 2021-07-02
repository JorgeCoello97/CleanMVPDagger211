package com.example.cleanmvpdagger211.observer;

import android.content.Context;

import com.example.cleanmvpdagger211.R;
import com.example.cleanmvpdagger211.mvp.view.IDictionaryView;
import com.example.domain.model.Word;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class DictionaryObserver extends DisposableObserver<ArrayList<Word>> {
    private IDictionaryView iDictionaryView;
    private Context context;

    public DictionaryObserver (IDictionaryView iDictionaryView, Context context){
        this.iDictionaryView = iDictionaryView;
        this.context = context;
    }

    @Override
    public void onNext(ArrayList<Word> value) {
        iDictionaryView.renderData(value);
    }

    @Override
    public void onError(Throwable e) {
        iDictionaryView.hideLoading();
        iDictionaryView.showMessage(context.getString(R.string.error_no_records));
    }

    @Override
    public void onComplete() {
        iDictionaryView.hideLoading();
    }
}
