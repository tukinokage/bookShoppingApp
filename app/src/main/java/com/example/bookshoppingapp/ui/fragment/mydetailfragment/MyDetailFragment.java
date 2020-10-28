package com.example.bookshoppingapp.ui.fragment.mydetailfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.presenter.MyDetailFragmentPresenter;
import com.example.bookshoppingapp.presenter.MyDetailFragmentPresenterImpl;
import com.example.bookshoppingapp.ui.RoundImageView;
import com.example.bookshoppingapp.ui.fragment.BaseFragment;
import com.example.bookshoppingapp.util.ToastTool;
import com.example.bookshoppingapp.util.UserInfoUtil;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;



/*************用户个人页**********/
public class MyDetailFragment extends BaseFragment implements BaseMyDetailFragment{


    /***********本部分可初始化控件
     * or
     * 变量
     * **************/


     /*组件初始化示例：
    @BindView(R.id.test_btn)
    Button button;
    */
     @BindView(R.id.fragment_myDetail_bought_layout)
     LinearLayout boughtLayout;

     @BindView(R.id.fragment_myDetail_history_layout)
     LinearLayout browsingHistoryLayout;

     @BindView(R.id.fragment_myDetail_quit_layout)
     LinearLayout quitLayout;

     @BindView(R.id.fragment_myDetail_nickName_textView)
     TextView userNameTextView;

     @BindView(R.id.mydetail_fragment_head_icon_imageView)
    RoundImageView headIconImageView;

     @BindView(R.id.fragment_myDetail_top_background_ImageView)
     ImageView topBackground;


     MyDetailFragmentPresenter myDetailFragmentPresenter;
    /*************************/


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_mydetail, container, false);
        return root;
        /*此处不要做任何初始化*/
    }


    @Override
    public void onStart() {
        super.onStart();



        /*以下部分可初始化数据*/

        //view
        userNameTextView.setText(UserInfoUtil.getUserName(getActivity()));


        //presenter
        myDetailFragmentPresenter = new MyDetailFragmentPresenterImpl(this);

        //初始头像
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_headicon);
        headIconImageView.setImageBitmap(bitmap);
        topBackground.setImageBitmap(bitmap);
    }

    @Override
    public void showToast(String dialogText) {
        ToastTool.showToastOnMainThread(dialogText, this.getContext());
    }

    @Override
    public void turnActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void initListener() {
        /*
        * 示例：button.setlistener(new View.OnClikedListenser{....})
        * or
        * button.setListenser(new 内部监听器类)
        * */

        boughtLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDetailFragmentPresenter.checkBoughtGoods();
            }
        });

        browsingHistoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDetailFragmentPresenter.checkBrowsingHistory();
            }
        });

        quitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDetailFragmentPresenter.quitLoginStatus();

            }
        });


    }


    /*监听器可编写为内部类
    * public class testListener implements 监听器接口{..
    * ...}
    * */

}
