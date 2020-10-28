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
import com.example.bookshoppingapp.model.listViewEntity.BrowsingHistoryListViewItem
;
import com.example.bookshoppingapp.util.LoadLocalPic;

import java.util.List;

public class BrowsingHistoryListAdapter extends BaseAdapter {
    private List<BrowsingHistoryListViewItem> booksList;
    private Context mContext;

    public BrowsingHistoryListAdapter(List<BrowsingHistoryListViewItem
> booksList, Context context){
        this.booksList = booksList;
        mContext = context;
    }

    public void setBooksList(List<BrowsingHistoryListViewItem
> booksList){
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_browsing_history_detail, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bookCoverImageView = convertView.findViewById(R.id.listview_browsing_history_book_cover_imageView);
            viewHolder.bookNameTextView = convertView.findViewById(R.id.listview_browsing_history_book_name_textView);
            viewHolder.dateTextView = convertView.findViewById(R.id.listview_browsing_history_book_time_textView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (!booksList.isEmpty()){
            BrowsingHistoryListViewItem book = booksList.get(position);
            viewHolder.bookCoverImageView.setImageBitmap(LoadLocalPic.getBookCoverBitmap(book.getIconPath(), (Activity) mContext));
            viewHolder.bookNameTextView.setText(book.getBookName());
            viewHolder.dateTextView.setText(book.getDate());
        }

        return convertView;
    }

    class ViewHolder{
        ImageView bookCoverImageView;

        TextView bookNameTextView;

        TextView dateTextView;

    }




}



