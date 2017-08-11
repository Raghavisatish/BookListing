package com.example.ragha.searchlists;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import java.util.List;

/**
 * Created by ragha on 8/8/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Initialize>> {

    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Initialize> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<Initialize> books = QueryClass.fetchBookData(mUrl);
        return books;
    }
}