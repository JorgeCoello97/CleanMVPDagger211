package com.example.cleanmvpdagger211.di.builder;

import com.example.cleanmvpdagger211.ui.DictionaryActivity;
import com.example.cleanmvpdagger211.di.module.DictionaryModule;
import com.example.cleanmvpdagger211.di.scope.PerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @PerActivity
    @ContributesAndroidInjector(modules = DictionaryModule.class)
    abstract DictionaryActivity contributeDictionaryActivity();
}
