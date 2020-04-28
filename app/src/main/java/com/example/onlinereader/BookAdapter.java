package com.example.onlinereader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> mBookList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View bookview;
        ImageView bookImage;
        TextView bookName;

        public ViewHolder(View view) {
            super(view);
            bookview = view;
            bookImage = (ImageView) view.findViewById(R.id.book_image);
            bookName = (TextView) view.findViewById(R.id.book_name);
        }

    }

    public BookAdapter(List<Book> bookList) {
        mBookList = bookList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.bookview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book = mBookList.get(position);
                Toast.makeText(view.getContext(), "you clicked view" + book.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Book book = mBookList.get(position);



        holder.bookImage.setImageResource(book.getImageId());
        holder.bookName.setText(book.getName());

    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }
}
