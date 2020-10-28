package com.example.bookshoppingapp.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.presenter.GoodDetailActivityPresenter;
import com.example.bookshoppingapp.presenter.GoodDetailActivityPresenterImpl;
import com.example.bookshoppingapp.util.LoadLocalPic;
import com.example.jianfeng.wisdomprogresshud.WisdomHUDStatus;
import com.example.jianfeng.wisdomprogresshud.WisdomProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;

/***商品详情页**/
public class GoodDetailActivity extends BaseActivity implements GoodDetailActivityBase{

    //VIEW
    @BindView(R.id.bookDetail_activity_bookName_textView)
    TextView book_name_tv;
    @BindView(R.id.bookDetail_activity_bookAuthor_textView)
    TextView book_author_tv;
    @BindView(R.id.bookDetail_activity_bookPrice_textView)
    TextView book_price_tv;
    @BindView(R.id.bookDetail_activity_bookIntroduction_textView)
    TextView book_introduction_tv;
    @BindView(R.id.bookDetail_activity_addToGoodsCart_button)
    Button addtoGoodsCart_bt;

    @BindView(R.id.bookDetail_activity_bookCover_imageView)
    ImageView bookCover;

    //CLASS
    BookItemInfo bookInfo;

    //PRESENTER
    GoodDetailActivityPresenter goodDetailActivityPresenter;

    /*此处声明变量或初始化组件*/


    /////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);
        ButterKnife.bind(this);

        initView();
        /*Log.i("log",bookInfo.getBookName()+"----"+bookInfo.getBookAuthor());*/

       goodDetailActivityPresenter = new GoodDetailActivityPresenterImpl(this);
    }



    private void initView(){

        bookInfo = (BookItemInfo) getIntent().getSerializableExtra("bookInfo");

        book_name_tv.setText(bookInfo.getBookName());
        book_price_tv.setText("¥ " + bookInfo.getCost());
        book_author_tv.setText( bookInfo.getBookAuthor());
        book_introduction_tv.setText(bookInfo.getIntroduction());
        bookCover.setImageBitmap(LoadLocalPic.getBookCoverBitmap(bookInfo.getIconPath(), this));

    }



    @Override
    public void showToast(String dialogText) {

    }

    @Override
    public void showWisdomHUDSucceed(String dialogText) {
        WisdomProgressHUD.start_onApplication(WisdomHUDStatus.Succee, this, dialogText);
    }

    @Override
    public void showWisdomHUDError(String dialogText) {
        WisdomProgressHUD.start_onApplication(WisdomHUDStatus.Error, this, dialogText);
    }


    @Override
    void initListener() {

        addtoGoodsCart_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodDetailActivityPresenter.addToShoppingCart(bookInfo.getBookId());

            }
        });

        /*此处注册监听器*/
    }

}

