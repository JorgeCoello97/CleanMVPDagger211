package com.example.cleanmvpdagger211.mvp.presenter;

import android.content.Context;

import com.example.cleanmvpdagger211.mvp.view.IDictionaryView;
import com.example.cleanmvpdagger211.observer.DictionaryClearObserver;
import com.example.cleanmvpdagger211.observer.DictionaryObserver;
import com.example.cleanmvpdagger211.observer.DictionaryTranslatorObserver;
import com.example.domain.factories.Factory;
import com.example.domain.interactor.ClearDictionaryUseCase;
import com.example.domain.interactor.GetDictionaryUseCase;
import com.example.domain.interactor.SaveTranslationUseCase;

import javax.inject.Inject;

public class DictionaryPresenter implements IPresenter {
    private IDictionaryView iDictionaryView;
    private GetDictionaryUseCase getDictionaryUseCase;
    private SaveTranslationUseCase saveTranslationUseCase;
    private ClearDictionaryUseCase clearDictionaryUseCase;
    private Context context;

    @Inject
    public DictionaryPresenter (IDictionaryView iDictionaryView,
                                GetDictionaryUseCase getDictionaryUseCase,
                                SaveTranslationUseCase saveTranslationUseCase,
                                ClearDictionaryUseCase clearDictionaryUseCase,
                                Context context){
        this.iDictionaryView = iDictionaryView;
        this.getDictionaryUseCase = getDictionaryUseCase;
        this.saveTranslationUseCase = saveTranslationUseCase;
        this.clearDictionaryUseCase = clearDictionaryUseCase;
        this.context = context;
    }

    public void initialize(){
        getDictionary();
    }

    public void getDictionary(){
        iDictionaryView.showLoading();
        getDictionaryUseCase.implementUseCase(new DictionaryObserver(iDictionaryView,context),null);
    }

    public void saveTranslation(String term, String translation){
        iDictionaryView.showLoading();
        saveTranslationUseCase.implementUseCase(
                new DictionaryTranslatorObserver(iDictionaryView,context),
                Factory.WordFactory.Create(term,translation));
    }

    public void clearDictionary(){
        iDictionaryView.showLoading();
        clearDictionaryUseCase.implementUseCase(new DictionaryClearObserver(iDictionaryView,context),null);
    }
    @Override
    public void destroy() {
        this.iDictionaryView = null;
        if(getDictionaryUseCase != null){
            getDictionaryUseCase.cancelSubscription();
        }
        if(saveTranslationUseCase != null){
            saveTranslationUseCase.cancelSubscription();
        }
        if(clearDictionaryUseCase != null){
            clearDictionaryUseCase.cancelSubscription();
        }
    }
}
