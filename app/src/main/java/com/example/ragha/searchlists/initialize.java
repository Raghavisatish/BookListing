package com.example.ragha.searchlists;

/**
 * Created by ragha on 8/8/2017.
 */

public class initialize {

        private String mTitle;
        private String[] mAuthors;
        private String mDescription;
        private String mSelfLink;


    public initialize(String bookTitle, String[] bookAuthors, String description, String bookLink) {

        mTitle=bookTitle;
        mAuthors=bookAuthors;
        mDescription=description;
        mSelfLink=bookLink;
    }


        public String getmTitle(){return mTitle;}
        public String[] getmAuthors(){return mAuthors;}
        public  String getmDescription(){return  mDescription;}
        public String getmSelfLink(){return mSelfLink;}

        }




