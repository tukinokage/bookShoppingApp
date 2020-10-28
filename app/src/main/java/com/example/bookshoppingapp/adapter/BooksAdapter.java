package com.example.bookshoppingapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.util.LoadLocalPic;

import java.util.List;
import java.util.Locale;

public class BooksAdapter extends BaseAdapter {
    private List<BookItemInfo> booksList;
    private Context mContext;

    public BooksAdapter(List<BookItemInfo> booksList, Context context){
        this.booksList = booksList;
        mContext = context;
    }

    public void setBooksList(List<BookItemInfo> booksList){
        this.booksList = booksList;
    }

    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public Object getItem(int position) {
        return booksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_fragment_books_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bookCoverImageView = convertView.findViewById(R.id.fragment_goods_book_cover_imageView);
            viewHolder.bookNameTextView = convertView.findViewById(R.id.fragment_goods_book_name_textView);
            viewHolder.authorTextView = convertView.findViewById(R.id.fragment_goods_book_author_textView);
            viewHolder.priceTextView = convertView.findViewById(R.id.fragment_goods_book_price_textView);
            viewHolder.saleNumsTextView = convertView.findViewById(R.id.fragment_goods_book_saleNum_textView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (!booksList.isEmpty()){
        BookItemInfo book = booksList.get(position);
        viewHolder.bookCoverImageView.setImageBitmap(LoadLocalPic.getBookCoverBitmap(book.getIconPath(), (Activity) mContext));
        viewHolder.bookNameTextView.setText(book.getBookName());
        viewHolder.authorTextView.setText("作者:" + book.getBookAuthor());
        viewHolder.priceTextView.setText("¥ " + String.valueOf(book.getCost()));
        viewHolder.saleNumsTextView.setText("销量" + String.valueOf(book.getSalesVolume()));
        }

        return convertView;
    }

    class ViewHolder{
        ImageView bookCoverImageView;

        TextView bookNameTextView;

        TextView authorTextView;

        TextView priceTextView;

        TextView saleNumsTextView;

    }




}



