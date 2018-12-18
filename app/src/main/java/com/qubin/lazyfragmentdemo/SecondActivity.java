package com.qubin.lazyfragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2018/12/18
 * @Describe:
 */
public class SecondActivity extends AppCompatActivity {

    private Unbinder bind;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bind = ButterKnife.bind(this);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ManageFragmentOne());
        fragments.add(new ManageFragmentTwo());
        fragments.add(new ManageFragmentThree());
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame,new ManageFragmentOne()).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @OnClick(value = {R.id.btn_one,R.id.btn_two,R.id.btn_three})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_one:
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.frame,new ManageFragmentOne()).commit();

                break;

            case R.id.btn_two:
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.frame,new ManageFragmentTwo()).commit();
                break;

            case R.id.btn_three:
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.frame,new ManageFragmentThree()).commit();
                break;

                default:
                    break;
        }
    }
}
