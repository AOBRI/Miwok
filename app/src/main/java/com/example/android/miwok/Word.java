package com.example.android.miwok;

/**
 * Created by Ahmed on 022 22-03-2018.
 */

public class Word extends Object {

    private String mDefaultTranslation;
    private String mMiwokTranslation;

    public Word(String defaultTranslation, String miwokTranslation) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setMiwokTranslation(String mMiwokTranslation) {
        this.mMiwokTranslation = mMiwokTranslation;
    }
}
