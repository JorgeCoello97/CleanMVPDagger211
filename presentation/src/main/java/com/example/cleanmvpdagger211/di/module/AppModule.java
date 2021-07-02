package com.example.cleanmvpdagger211.di.module;

import android.content.Context;

import com.example.cleanmvpdagger211.App;
import com.example.data.DictionaryRepositoryImplementation;
import com.example.domain.repositorio.IDictionaryRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Module
public  class AppModule {
    @Provides
    @Singleton
    static Context provideContext(App application){
       return application.getApplicationContext();
    }

    @Provides
    @Singleton
    static Scheduler provideScheduler(){
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    static IDictionaryRepository provideDictionaryRepository(DictionaryRepositoryImplementation dictionaryRepositoryImplementation){
        return dictionaryRepositoryImplementation;
    }
}
