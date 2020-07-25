package com.ntec.pokelearn;

public class MimeQuestions {
    private int mImage;
    private String mFacts, ansCheck;

    public MimeQuestions(String ansCheck, int mImage, String mFacts){
        this.mImage = mImage;
        this.mFacts = mFacts;
        this.ansCheck = ansCheck;
    }

    public int getmImage(){
        return mImage;
    }

    public String getmFacts(){
        return mFacts;
    }

    public String getAnsCheck() {return ansCheck;}
}
