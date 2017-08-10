package com.example.ragha.searchlists;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ragha on 8/8/2017.
 */

public class SearchAdapter extends ArrayAdapter<initialize> {



    public SearchAdapter(Context context,List<initialize> quakes) {super(context, 0, quakes);}


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);}

        initialize cuttentItem=getItem(position);

        TextView bookName= (TextView) listItemView.findViewById(R.id.bookTitle);
        bookName.setText(cuttentItem.getmTitle());

        TextView authorName= (TextView) listItemView.findViewById(R.id.authors);
        String authors = "";
        for (int i = 0; i < cuttentItem.getmAuthors().length; i++){
            if (authors.isEmpty()) {
                authors = cuttentItem.getmAuthors()[i];
            } else{
                authors = authors + " and " + cuttentItem.getmAuthors()[i];
            }
            authorName.setText(authors);
        }

        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.description);
        String bookDescription = cuttentItem.getmDescription();
        descriptionTextView.setText(bookDescription);



        return listItemView;

    }
}

