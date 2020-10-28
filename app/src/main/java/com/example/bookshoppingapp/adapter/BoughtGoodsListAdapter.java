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
import com.example.bookshoppingapp.model.listViewEntity.BoughtHistoryListViewItem;
import com.example.bookshoppingapp.util.LoadLocalPic;

import java.util.List;

public class BoughtGoodsListAdapter extends BaseAdapter {
    private List<BoughtHistoryListViewItem> booksList;
    private Context mContext;

    public BoughtGoodsListAdapter(List<BoughtHistoryListViewItem> booksList, Context context){
        this.booksList = booksList;
        mContext = context;
    }

    public void setBooksList(List<BoughtHistoryListViewItem> booksList){
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_bought_goods_detail, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bookCoverImageView = convertView.findViewById(R.id.listview_bought_goods_book_cover_imageView);
            viewHolder.bookNameTextView = convertView.findViewById(R.id.listview_bought_goods_book_name_textView);
            viewHolder.priceTextView = convertView.findViewById(R.id.listview_bought_goods_book_price_textView);
            viewHolder.saleNumsTextView =  convertView.findViewById(R.id.listview_bought_goods_books_num_textView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (!booksList.isEmpty()){
            BoughtHistoryListViewItem book = booksList.get(position);
            viewHolder.bookCoverImageView.setImageBitmap(LoadLocalPic.getBookCoverBitmap(book.getIconPath(), (Activity) mContext));
            viewHolder.bookNameTextView.setText(book.getBookName());
            viewHolder.priceTextView.setText("¥ " + String.valueOf(book.getCost()));
            viewHolder.saleNumsTextView.setText("数量:" + String.valueOf(book.getNum()));
        }

        return convertView;
    }

    class ViewHolder{
        ImageView bookCoverImageView;

        TextView bookNameTextView;


        TextView priceTextView;

        TextView saleNumsTextView;

    }




}



