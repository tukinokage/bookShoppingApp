package com.example.bookshoppingapp.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.ui.fragment.goodsfragment.GoodsFragment;
import com.example.bookshoppingapp.ui.fragment.mydetailfragment.MyDetailFragment;
import com.example.bookshoppingapp.ui.fragment.shoppingcartfragment.ShoppingCartFragment;
import com.example.bookshoppingapp.util.ActivityCollectorUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.ButterKnife;


/**
 *
 * 三个fragment在对应fragment界面刷新
 *
**/

/**********主界面*********/
public class MainActivity extends BaseActivity implements MainActivityBase{


    /*************导航栏相关*************/
    private FragmentTransaction ft;
    private FrameLayout contentLayout;

    private GoodsFragment goodsfragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MyDetailFragment myDetailFragment;

    private Fragment mContent;

    BottomNavigationView navView;


    /**************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        init();
        initNationListener();
        initFramgmenTransaction();

        ActivityCollectorUtil.addActivity(this);


    }

/***********初始化导航栏*******************/
    private void initFramgmenTransaction() {

        if (goodsfragment != null && goodsfragment.isAdded() ) {
            ft.remove(goodsfragment);
        }
        if (shoppingCartFragment != null && shoppingCartFragment.isAdded() ) {
            ft.remove(shoppingCartFragment);
        }
        if (myDetailFragment != null && myDetailFragment.isAdded() ) {
            ft.remove(myDetailFragment);
        }
        ft.commitAllowingStateLoss();
        goodsfragment = null;
        shoppingCartFragment = null;
        myDetailFragment = null;

        switchContent(1);
    }

    private void init() {
        navView = findViewById(R.id.nav_view);
        ft = getSupportFragmentManager().beginTransaction();
        contentLayout = findViewById(R.id.nav_host_framelayout);
        mContent = null;
    }



    /***********注册导航栏监听器*******************/
    private void initNationListener(){

        navView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_goods:
                        switchContent(1);
                        return true;
                    case R.id.navigation_shopping_cart:
                        switchContent(2);
                        return true;
                    case R.id.navigation_my_detail:
                        switchContent(3);
                        return true;
                }

                return false;

            }
        });


    }




    /**导航栏切换fragment**/
    private void switchContent(int itemNum){

        Fragment mfragment = null;
        ft = getSupportFragmentManager().beginTransaction();



        if(itemNum == 1) {
            if(goodsfragment == null) {
                goodsfragment = new GoodsFragment();
            }
            mfragment = goodsfragment;
        }

        else if(itemNum == 2) {
            if(shoppingCartFragment == null) {
                shoppingCartFragment = new ShoppingCartFragment();
            }
            mfragment = shoppingCartFragment;
        }

        else if(itemNum == 3) {
            if(myDetailFragment == null) {
                myDetailFragment = new MyDetailFragment();
            }
            mfragment = myDetailFragment;
        }

        if(mContent == null){
            ft.add(contentLayout.getId(), mfragment).commit();
            mContent = mfragment;
        }else {
            if (!mfragment.isAdded()) {

                ft.hide(mContent).add(contentLayout.getId(), mfragment)
                        .commitAllowingStateLoss();

            } else {
                ft.hide(mContent).show( mfragment)
                        .commitAllowingStateLoss();
            }
            mContent = mfragment;
        }

    }



    @Override
    public void showToast(String dialogText) {

    }

    @Override
    void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
