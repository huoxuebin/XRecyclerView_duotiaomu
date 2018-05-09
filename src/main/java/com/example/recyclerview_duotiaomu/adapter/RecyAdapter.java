package com.example.recyclerview_duotiaomu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerview_duotiaomu.DeatilsBean;

import com.example.recyclerview_duotiaomu.R;
import com.example.recyclerview_duotiaomu.jiekou.OnItemClickListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by huoxuebin on 2018/5/9.
 */

public class RecyAdapter  extends RecyclerView.Adapter{

    List<DeatilsBean.DataBean> data;
    Context context;
    private OnItemClickListener onItemCLickenter;

    public RecyAdapter(List<DeatilsBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 0:
                view = View.inflate(context, R.layout.item,null);
                holder = new MyHolder(view);
                break;
            case 1:
                view = View.inflate(context,R.layout.item2,null);
                holder = new MyHolder1(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)){
            case 0:
                final MyHolder holder1 = (MyHolder) holder;
                String images = data.get(position).getImages();
                String[] split = images.split("\\|");


                holder1.yi.setImageURI(split[0]);
      /*          RoundingParams roundingParams2 = RoundingParams.fromCornersRadius(5f);
                roundingParams2.setRoundAsCircle(true);
                holder1.yi.getHierarchy().setRoundingParams(roundingParams2);*/
                holder1.title.setText(data.get(position).getTitle());
                break;
            case 1:
                final MyHolder1 myHolder1 = (MyHolder1) holder;
                String images2 = data.get(position).getImages();
                String[] split2 = images2.split("\\|");
                myHolder1.simpleDraweeView.setImageURI(split2[0]);
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                roundingParams.setRoundAsCircle(true);
                myHolder1.simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
                myHolder1.name.setText(data.get(position).getTitle());
                break;


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCLickenter.onItemClick(position);
            }
        });
    }



    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0){
            return 0;
        }else {
            return 1;
        }
    }
    @Override
    public int getItemCount() {
        return data== null ? 0 : data.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public SimpleDraweeView yi;

        public MyHolder(View itemView) {
            super(itemView);
            yi = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
        }
    }
    class MyHolder1 extends RecyclerView.ViewHolder {
        TextView name;
        public   SimpleDraweeView simpleDraweeView;

        public MyHolder1(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title2);
            simpleDraweeView = itemView.findViewById(R.id.img2);
        }
    }

    public void setOnItemCLickenter(OnItemClickListener onItemCLickenter){
        this.onItemCLickenter=onItemCLickenter;

    }
}
