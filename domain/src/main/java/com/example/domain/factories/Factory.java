package com.example.domain.factories;

import com.example.domain.model.Word;

public class Factory {
    public static class WordFactory {
        public static Word Create(String term, String translation){
            return new Word(term, translation);
        }
    }
}
