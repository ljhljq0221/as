package com.example.recyclerviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit>mFruitList;
    //ViewHolder 自定义的类 继承自RecyclerView.ViewHolder
    // 在ViewHolder中添加了fruitView变量包窜子项最外层布局的实例
    //随后在 onCreateViewHolder注册实例
    public class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        //构造函数中传入一个View参数，这个参数通常为RecycleView子项的最外参数
        // 利用View.findViewById获取布局中的ImageView和TextView实例
        public ViewHolder(View view){
            super(view);
            fruitView=view;
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
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item,parent,false);//加载布局
        //随后在 onCreateViewHolder注册点击事件
       final ViewHolder holder=new ViewHolder(view);
       holder.fruitView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int position =holder.getAdapterPosition();
               Fruit fruit= mFruitList.get(position);
               Toast.makeText(v.getContext(), "you clicked view "+fruit.getName(),
                       Toast.LENGTH_SHORT).show();
           }
       });
       holder.fruitImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int position =holder.getAdapterPosition();
               Fruit fruit= mFruitList.get(position);
               Toast.makeText(v.getContext(), "you clicked image "+fruit.getName(),
                       Toast.LENGTH_SHORT).show();
           }
       });
        return holder;
    }
    // onBindViewHolder 方法对RecyclerView的子项进行赋值，通过mFruitList.get(position)获取当前滚动的Fruit实例
    // 随后将数据设置给ImageView和TextView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }
    //getItemCount 获取RecyclerView一共有多少子项，直接返回数据长度即可
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
