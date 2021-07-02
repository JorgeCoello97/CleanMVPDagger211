package com.example.data.factory;

import android.content.Context;

import com.example.data.datastore.IDictionaryDataStore;
import com.example.data.datastore.local.LocalDictionaryDataStore;
import com.example.data.datastore.remote.RemoteDictionaryDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DictionaryDataStoreFactory implements IDataStoreFactory {
    Context context;

    @Inject
    public DictionaryDataStoreFactory(Context context){
        this.context = context;
    }

    @Override
    public IDictionaryDataStore Remote() {
        return new RemoteDictionaryDataStore(context);
    }

    @Override
    public IDictionaryDataStore Local() {
        return new LocalDictionaryDataStore(context);
    }
}
