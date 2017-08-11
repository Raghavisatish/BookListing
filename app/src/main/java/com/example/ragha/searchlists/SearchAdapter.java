package com.example.ragha.searchlists;

/**
 * Created by ragha on 8/8/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    ArrayList<Book1> mBook;
    MainActivity mContext;

    private static OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(Book1 book);
    }

    public SearchAdapter(MainActivity context, ArrayList<Book1> book, OnItemClickListener listener) {
        mContext = context;
        mBook = book;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        Book1 book = mBook.get(position);
        holder.bookTitle.setText(book.getTitleId());
        holder.bookAuthor.setText(book.getAuthorId());
        Picasso.with(mContext).load(book.getThumbnailId()).into(holder.bookImageView);
        holder.bookDescription.setText(book.getmDescription());
        holder.bind(mBook.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mBook.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImageView;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            bookImageView = (ImageView) itemView.findViewById(R.id.BookCover);
            bookTitle = (TextView) itemView.findViewById(R.id.BookTitle);
            bookAuthor = (TextView) itemView.findViewById(R.id.BookAuthor);
            bookDescription = (TextView) itemView.findViewById(R.id.Bookdescription);
        }

        public void bind(final Book1 book, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(book);
                }
            });
        }
    }

    public void clear() {
        mBook.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Book1> book) {
        mBook.addAll(book);
        notifyDataSetChanged();
    }
}
