package com.example.cleanmvpdagger211.di.component;

import com.example.cleanmvpdagger211.App;
import com.example.cleanmvpdagger211.di.builder.BuildersModule;
import com.example.cleanmvpdagger211.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class, BuildersModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application (App application);
        AppComponent build();
    }
    void inject(App app);
}
