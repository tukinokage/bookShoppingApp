package com.example.bookshoppingapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.model.listViewEntity.CartItem;
import com.example.bookshoppingapp.ui.fragment.shoppingcartfragment.ShoppingCartFragment;
import com.example.bookshoppingapp.util.LoadLocalPic;

import java.util.List;

public class GoodsCartItemAdapter extends BaseAdapter {
    private List<CartItem> cartItemList;
    private Context mContext;

    public GoodsCartItemAdapter(List<CartItem> cartItemList, Context context){
        this.cartItemList = cartItemList;
        mContext = context;
    }

    public void setCartItemList(List<CartItem> cartItemList){
        this.cartItemList = cartItemList;
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_shopping_cart_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bookCoverImageView = convertView.findViewById(R.id.fragment_shopping_cart_bookCover_imageView);
            viewHolder.bookNameTextView = convertView.findViewById(R.id.fragment_shopping_cart_bookName_textView);
            viewHolder.priceTextView = convertView.findViewById(R.id.fragment_shopping_cart_item_price_textView);
            viewHolder.numsTextView = convertView.findViewById(R.id.fragment_shopping_cart_bookNum);
            viewHolder.checkBox = convertView.findViewById(R.id.fragment_shopping_cart_checkBox);
            viewHolder.addButton = convertView.findViewById(R.id.fragment_shopping_cart_addNum_btn);
            viewHolder.subButton = convertView.findViewById(R.id.fragment_shopping_cart_subNum_btn);


            viewHolder.checkBox.setOnCheckedChangeListener(new ShoppingCartFragment.checkListener(position));
            viewHolder.subButton.setOnClickListener(new ShoppingCartFragment.subBtnListener(position));
            viewHolder.addButton.setOnClickListener(new ShoppingCartFragment.addBtnListener(position));

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (!cartItemList.isEmpty()){
            CartItem item = cartItemList.get(position);
            viewHolder.bookCoverImageView.setImageBitmap(LoadLocalPic.getBookCoverBitmap(item.getIconPath(), (Activity) mContext));
            viewHolder.bookNameTextView.setText(item.getBookName());
            viewHolder.priceTextView.setText(String.valueOf(item.getCost()));
            viewHolder.numsTextView.setText(String.valueOf(item.getNum()));
        }

        return convertView;
    }

    static class ViewHolder{
        ImageView bookCoverImageView;

        TextView bookNameTextView;

        TextView priceTextView;

        TextView numsTextView;

        CheckBox checkBox;

        Button addButton;
        Button subButton;

    }




}



