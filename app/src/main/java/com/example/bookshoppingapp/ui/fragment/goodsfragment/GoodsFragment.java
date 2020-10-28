package com.example.bookshoppingapp.ui.fragment.goodsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.adapter.BookTypeAdapter;
import com.example.bookshoppingapp.adapter.BooksAdapter;
import com.example.bookshoppingapp.model.BookTypeEnum;
import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.model.listViewEntity.BookTypeListItem;
import com.example.bookshoppingapp.presenter.GoodsFragmentPresenter;
import com.example.bookshoppingapp.presenter.GoodsFragmentPresenterImpl;
import com.example.bookshoppingapp.ui.activity.GoodDetailActivity;
import com.example.bookshoppingapp.ui.fragment.BaseFragment;
import com.example.bookshoppingapp.util.ToastTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/*************商品列表显示**********/
public class GoodsFragment extends BaseFragment implements BaseGoodsFragment {

    /***********本部分可初始化控件
     * or
     * 变量
     * or
     * 声明
     * **************/

    private List<BookTypeListItem> typeList;
    private List<BookItemInfo> booksList;

    @BindView(R.id.fragment_goods_types)
     ListView typeListView;

    @BindView(R.id.fragment_goods_books)
    ListView booksListView;

    BookTypeAdapter typeAdapter;
    BooksAdapter booksAdapter;

    GoodsFragmentPresenter goodsFragmentPresenter;

/*/* presenter使用示例:
    GoodsFragmentPresenter presenter;*/

     /*组件初始化示例：
    @BindView(R.id.test_btn)
    Button button;
    */
    /*************************/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_goods, container, false);
        return root;
        /*此处不要做任何初始化*/
    }


    @Override
    public void onStart() {

        super.onStart();
        /*以下部分可初始化数据*/

        /*/* presenter使用示例:
        presenter = new GoodsFragmentPresenterImpl(this);
         */
        goodsFragmentPresenter = new GoodsFragmentPresenterImpl(this);

        initListViewData();


    }


    @Override
    public void onResume() {
        super.onResume();
        booksAdapter.notifyDataSetChanged();
    }

    private void initListViewData(){
        //type列表初始化
        typeList = new ArrayList<BookTypeListItem>();

        for (BookTypeEnum  typeEnum :BookTypeEnum.values()) {
            BookTypeListItem item = new BookTypeListItem();
            item.setBookTypeEnum(typeEnum);
            item.setIsCheck(false);

            typeList.add(item);
        }

        typeAdapter = new BookTypeAdapter(typeList, getContext());
        typeListView.setAdapter(typeAdapter);

        //books列表默认值初始化
        booksList = new ArrayList<BookItemInfo>();

        BookItemInfo book = new BookItemInfo();
        book.setBookName("测试书名");
        book.setBookAuthor("测试作者");
        book.setCost((float) 22.33);
        book.setSalesVolume(50);

        booksList.add(book);
        booksList.add(book);

        booksAdapter = new BooksAdapter(booksList, getContext());
        booksListView.setAdapter(booksAdapter);

        //初始化两个listView状态
        goodsFragmentPresenter.switchType(typeList, 0);
        typeList.get(0).setIsCheck(true);

    }

    @Override
    protected void initListener() {

        /*
         * 示例：button.setlistener(new View.OnClikedListenser{....})
         * or
         * button.setListenser(new 内部监听器类)
         * */

       /* presenter使用示例:button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doSomething();
            }
        });*/


       //切换类型监听器
       typeListView.setOnItemClickListener(new itemOnclickLisenter());
       booksListView.setOnItemClickListener(new BookItemOnclickLisenter());

    }




    /*监听器可编写为内部类
     * public class testListener implements 监听器接口{..
     * ...}
     * */


    /****************************内部类监听器******************************************/
   class itemOnclickLisenter implements AdapterView.OnItemClickListener {

        public itemOnclickLisenter(){

        }

       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           for (int i = 0; i < typeList.size(); i++) {
               if(i == position){
                   typeList.get(i).setIsCheck(true);
               }else {
                   typeList.get(i).setIsCheck(false);
               }
           }

           goodsFragmentPresenter.switchType(typeList, position);
           typeAdapter.notifyDataSetChanged();
       }
   }


    class BookItemOnclickLisenter implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goodsFragmentPresenter.updateBrowsingHistory(booksList, position);
                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
                intent.putExtra("bookInfo",  booksList.get(position));
                startActivity(intent);
        }
    }


    /**********************************************************************/


    @Override
    public void showToast(String dialogText) {
        ToastTool.showToastOnMainThread(dialogText, this.getContext());
    }

    @Override
    public void updateBookListView(List<BookItemInfo> booksList) {
        this.booksList = booksList;
        booksAdapter.setBooksList(this.booksList);
        if(booksList.isEmpty()){
            ToastTool.showToastOnMainThread("没有找到书本", this.getContext());
        }
        booksAdapter.notifyDataSetChanged();
    }

}
