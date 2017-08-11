package com.example.ragha.searchlists;

import java.util.ArrayList;

/**
 * Created by ragha on 8/8/2017.
 */

public class Book1 {

    private String mThumbnail;
    private String mTitle;
    private ArrayList<String> mAuthor;
    private String mDescription;
    private String mInfoLinkUrl;

    public Book1(String thumbnail, String title, ArrayList<String> author, String desc, String infoLinkUrl) {
        mThumbnail = thumbnail;
        mTitle = title;
        mAuthor = author;
        mInfoLinkUrl = infoLinkUrl;
        mDescription = desc;
    }

    public String getThumbnailId() {
        return mThumbnail;
    }

    public String getTitleId() {
        return mTitle;
    }

    public String getAuthorId() {
        String authors = checkAuthors();
        return authors;
    }

    public String checkAuthors() {
        String authors = mAuthor.get(0);
        if (mAuthor.size() > 1) {
            for (int i = 1; i < mAuthor.size(); i++) {
                authors += "\n" + mAuthor.get(i);
            }
        }
        return authors;
    }

    public String getInfoLinkId() {
        return mInfoLinkUrl;
    }

    public String getmDescription() {
        return mDescription;
    }
}