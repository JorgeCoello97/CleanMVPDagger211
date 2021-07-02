package com.example.domain.model;

public class Word {
    private String term;
    private String translation;

    public Word(String term, String translation) {
        this.term = term;
        this.translation = translation;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
