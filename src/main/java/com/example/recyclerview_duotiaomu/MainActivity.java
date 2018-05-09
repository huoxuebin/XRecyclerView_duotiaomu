package com.example.recyclerview_duotiaomu;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.Toast;


import com.example.recyclerview_duotiaomu.adapter.RecyAdapter;
import com.example.recyclerview_duotiaomu.utils.BaseObserver;
import com.example.recyclerview_duotiaomu.utils.RetrofitManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        xRecyclerView = findViewById(R.id.ry);
        //上拉刷新 下拉加载
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                        xRecyclerView.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        xRecyclerView.loadMoreComplete();

                    }
                }, 2000);
            }
        });



        Map<String, String> map = new HashMap<>();
        map.put("keywords", "笔记本");
        map.put("source", "android");
        RetrofitManager.post("product/searchProducts?", map, new BaseObserver<DeatilsBean>() {


            @Override
            public void success(DeatilsBean deatilsBean) {

                List<DeatilsBean.DataBean> data = deatilsBean.getData();



                xRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, OrientationHelper.VERTICAL,false));
                RecyAdapter adapter = new RecyAdapter(data,MainActivity.this);
                xRecyclerView.setAdapter(adapter);



            }

            @Override
            public void failure(int code) {

            }

        });
    }
}
