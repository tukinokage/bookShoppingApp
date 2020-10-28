package com.example.bookshoppingapp.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookshoppingapp.R;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {


    public View root;

    @Override
    public void onStart() {
        super.onStart();
        ButterKnife.bind(this, root);
        initListener();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    /*****注册监听器**********/
   protected abstract void initListener();
}
