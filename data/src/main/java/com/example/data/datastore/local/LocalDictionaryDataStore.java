package com.example.data.datastore.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.data.datastore.IDictionaryDataStore;
import com.example.domain.model.Word;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Observable;

public class LocalDictionaryDataStore implements IDictionaryDataStore {
    Context context;
    private final SharedPreferences sharedPreferences;
    private static final String NOVALUE = null;
    private static final String DICTIONARYRECORDS = "DictionaryRecords";

    public LocalDictionaryDataStore(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(DICTIONARYRECORDS, Context.MODE_PRIVATE);
    }

    private ArrayList<Word> getRecords(){
        String dictionaryRecords = sharedPreferences.getString(DICTIONARYRECORDS, NOVALUE);
        if (dictionaryRecords != null){
            Type listOfWordsType = new TypeToken<ArrayList<Word>>(){}.getType();
            Gson gson = new Gson();
            return gson.fromJson(dictionaryRecords,listOfWordsType);
        }
        return new ArrayList<Word>();
    }

    private int existsWord(ArrayList<Word> records, Word wordToFind){
        int index = -1;
        for (Word word: records) {
            index++;
            if ((wordToFind.getTerm().compareToIgnoreCase(word.getTerm())) == 0){
                return index;
            }
        }
        return -1;
    }

    private ArrayList<Word> SortRecords(ArrayList<Word> records){
        Collections.sort(records,(Word word1, Word word2) ->{
            return word1.getTerm().compareToIgnoreCase(word2.getTerm());
        });
        return records;
    }

    @Override
    public Observable<ArrayList<Word>> GetDictionary() {
       /*return Observable.create(new ObservableOnSubscribe<ArrayList<Word>>() {

           @Override
           public void subscribe(ObservableEmitter<ArrayList<Word>> emitter) throws Exception {
               try {
                   ArrayList<Word> records = getRecords();
                   records = SortRecords(records);
                   emitter.onNext(records);
                   emitter.onComplete();
               } catch (Exception e){
                   emitter.onError(e);
               }
           }
       });*/

       // EL MISMO CODIGO ANTERIOR PERO CON EXPRESIONES LAMBDA
        return Observable.create(emitter -> {
           try {
               ArrayList<Word> records = getRecords();
               records = SortRecords(records);
               emitter.onNext(records);
               emitter.onComplete();
           }catch (Exception e){
               emitter.onError(e);
           }
        });
    }

    @Override
    public Observable<ArrayList<Word>> SaveTranslation(Word word) {
       return Observable.create(emitter ->{
          try {
              Type listOfWordsType = new TypeToken<ArrayList<Word>>(){}.getType();
              ArrayList<Word> records = getRecords();
              word.setTerm(word.getTerm().toUpperCase());
              int indexWordTranslation = existsWord(records, word);
              if (indexWordTranslation > -1) {
                  records.set(indexWordTranslation,word);
              }
              else if (!word.getTerm().isEmpty()){
                  records.add(word);
                  records = SortRecords(records);

                  Gson gson = new Gson();
                  sharedPreferences.edit().putString(DICTIONARYRECORDS, gson.toJson(records,listOfWordsType)).apply();
                  emitter.onNext(records);
                  emitter.onComplete();
              }
          }catch (Exception e){
              emitter.onError(e);
          }
       });
    }

    @Override
    public Observable<Boolean> ClearDictionary() {
        return Observable.create(emitter ->{
           try {
               sharedPreferences.edit().remove(DICTIONARYRECORDS);
               emitter.onNext(true);
               emitter.onComplete();
           }catch (Exception e){
               emitter.onError(e);
           }
        });
    }
}
