package com.qubin.lazyfragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2018/12/17
 * @Describe:
 */
public abstract class BaseManagerFragment extends Fragment {

    private Unbinder bind;

    public BaseManagerFragment(){}

    private boolean isVisibilty = false;
    private boolean isCreatView = false;
    private boolean isLoadData = false;

    abstract public void initData();
    abstract public int initLayout();
    abstract public void initView();
    abstract public void loadData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(),container,false);
        bind = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.isCreatView = true;
        tryToLoadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.isVisibilty = hidden;
        tryToLoadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public void tryToLoadData(){
        if (!isVisibilty && isCreatView && !isLoadData){
            loadData();
            isLoadData = true;
        }
    }


}
