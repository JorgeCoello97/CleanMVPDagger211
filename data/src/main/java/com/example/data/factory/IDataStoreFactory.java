package com.example.data.factory;

import com.example.data.datastore.IDataStore;

public interface IDataStoreFactory {
    IDataStore Remote();
    IDataStore Local();
}
