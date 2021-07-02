package com.example.cleanmvpdagger211.di.module;

import android.content.Context;

import com.example.cleanmvpdagger211.ui.DictionaryActivity;
import com.example.cleanmvpdagger211.mvp.presenter.DictionaryPresenter;
import com.example.cleanmvpdagger211.mvp.view.IDictionaryView;
import com.example.domain.interactor.ClearDictionaryUseCase;
import com.example.domain.interactor.GetDictionaryUseCase;
import com.example.domain.interactor.SaveTranslationUseCase;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DictionaryModule {
    @Provides
    static DictionaryPresenter provideDictionaryPresenter(Context context,
                                                          IDictionaryView iDictionaryView,
                                                          GetDictionaryUseCase getDictionaryUseCase,
                                                          SaveTranslationUseCase saveTranslationUseCase,
                                                          ClearDictionaryUseCase clearDictionaryUseCase)
    {
        return new DictionaryPresenter(iDictionaryView,getDictionaryUseCase,
                saveTranslationUseCase,clearDictionaryUseCase,context);
    }

    @Binds
    abstract IDictionaryView provideDictionaryView(DictionaryActivity dictionaryActivity);
}
