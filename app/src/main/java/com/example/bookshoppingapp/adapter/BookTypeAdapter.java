package com.example.bookshoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.model.listViewEntity.BookTypeListItem;

import java.util.List;

public class BookTypeAdapter extends BaseAdapter {
    private List<BookTypeListItem> typeList;
    private Context mContext;

    public BookTypeAdapter(List<BookTypeListItem> typeEnumsList, Context context){
        this.typeList = typeEnumsList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return typeList.size();
    }

    @Override
    public Object getItem(int position) {
        return typeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {


            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_type_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.backgroundLayout = convertView.findViewById(R.id.listView_type_background_layout);
            viewHolder.typeTextView = convertView.findViewById(R.id.listView_type_textView);

           // viewHolder.backgroundLayout.setOnClickListener(new itemOnclickLisenter(position, typeList, this));

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if(typeList.get(position).getIsCheck()){
            viewHolder.backgroundLayout.setBackgroundColor(mContext.getResources().getColor(R.color.themeColor));
        }else {
            viewHolder.backgroundLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

        viewHolder.typeTextView.setText(typeList.get(position).getBookTypeEnum().getTypeChinese());


        return convertView;
    }



    class ViewHolder{
        LinearLayout backgroundLayout;

        TextView typeTextView;
    }


/*    class itemOnclickLisenter implements View.OnClickListener{

        private int positon;
        private List<BookTypeListItem> typeList;
        private BaseAdapter adapter;

        public itemOnclickLisenter(int positon, List<BookTypeListItem> typeList, BaseAdapter adapter){
            this.positon = positon;
            this.typeList = typeList;
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            for (int i = 0; i < typeList.size(); i++) {
                if(i == positon){
                    typeList.get(i).setIsCheck(true);
                }else {
                    typeList.get(i).setIsCheck(false);
                }
            }

            adapter.notifyDataSetChanged();



        }
    }*/
}



