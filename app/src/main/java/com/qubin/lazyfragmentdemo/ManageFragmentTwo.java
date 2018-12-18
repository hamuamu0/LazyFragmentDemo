package com.qubin.lazyfragmentdemo;

import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2018/12/18
 * @Describe:
 */
public class ManageFragmentTwo extends BaseManagerFragment {
    @BindView(R.id.txt_message)
    TextView txtMessage;
    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.fragment_demo;
    }

    @Override
    public void initView() {
        txtMessage.setText("bbbbbbb");
    }

    @Override
    public void loadData() {
        Log.i("result==========","bbbbbbbbbb");
    }
}
