package com.example.toolbest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mFruitList;
    //ViewHolder 自定义的类 继承自RecyclerView.ViewHolder
    // 在ViewHolder中添加了fruitView变量包窜子项最外层布局的实例
    //随后在 onCreateViewHolder注册实例
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;
        //构造函数中传入一个View参数，这个参数通常为RecycleView子项的最外参数
        // 利用View.findViewById获取布局中的ImageView和TextView实例
        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            fruitImage=(ImageView) view.findViewById(R.id.fruit_image);
            fruitName=(TextView) view.findViewById(R.id.fruit_name);
        }
    }
    //FruitAdapter 构造函数，传入一个List<Fruit>参数，并赋值给全局变量mFruitList
    public FruitAdapter(List<Fruit>fruitList) {
        mFruitList=fruitList;
    }

    @NonNull
    //重写onCreateViewHolder（）方法 用于创建ViewHolder实例，加载的布局传入，返回一个ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext)
                .inflate(R.layout.fruit_item,parent,false);//加载布局
       // return new ViewHolder(view);

       //传入水果活动
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =holder.getAdapterPosition();
                System.out.println(position);
                Fruit fruit= mFruitList.get(position);
                Intent intent=new Intent(mContext,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                mContext.startActivity(intent);
            }
        });

        return  holder;
    }
    // onBindViewHolder 方法对RecyclerView的子项进行赋值，通过mFruitList.get(position)获取当前滚动的Fruit实例
    // 随后将数据设置给ImageView和TextView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }
    //getItemCount 获取RecyclerView一共有多少子项，直接返回数据长度即可
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
