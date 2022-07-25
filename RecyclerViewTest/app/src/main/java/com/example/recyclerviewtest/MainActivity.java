package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import org.apache.http.conn.scheme.HostNameResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList=new ArrayList<>();

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycle_view);
        //LayoutManager 用于指定RecyclerView的布局方式，这里使用LinearLayoutManager 线性布局
        //和ListView类似
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }*/
  /*
  //横向移动布局
  @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       initFruits();
       RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycle_view);
       //LayoutManager 用于指定RecyclerView的布局方式，这里使用LinearLayoutManager 线性布局
       //和ListView类似
       LinearLayoutManager layoutManager=new LinearLayoutManager(this);
       //调用LinearLayoutManager.setOrientation（）设置布局排列方向，默认纵向，
       //LinearLayoutManager.HORIZONTAL 表示横向排列
       layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
       recyclerView.setLayoutManager(layoutManager);
       FruitAdapter adapter=new FruitAdapter(fruitList);
       recyclerView.setAdapter(adapter);
   }*/
    //瀑布流布局
   /*@Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       initFruits();
       RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycle_view);
       //LayoutManager 用于指定RecyclerView的布局方式，
       //创建了一个StaggeredGridLayoutManager实例，
       // 构造函数会接受两个参数，第一个指定布局的列数，第二各指定布局的排列方向
       //                                   StaggeredGridLayoutManager.VERTICAL 布局纵向排列
       StaggeredGridLayoutManager layoutManager=new
               StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
       recyclerView.setLayoutManager(layoutManager);
       FruitAdapter adapter=new FruitAdapter(fruitList);
       recyclerView.setAdapter(adapter);
   }*/
    //网格布局
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       initFruits();
       RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycle_view);
       //LayoutManager 用于指定RecyclerView的布局方式，
       //创建了一个GridLayoutManager实例，
       // 构造函数会接受两个参数，第一个上下文，第二各指定布局的列数
       //                                  StaggeredGridLayoutManager.VERTICAL 布局纵向排列
       GridLayoutManager layoutManager=new
               GridLayoutManager(MainActivity.this,4);
       layoutManager.setOrientation(RecyclerView.HORIZONTAL);//设置横向移动
       recyclerView.setLayoutManager(layoutManager);
       FruitAdapter adapter=new FruitAdapter(fruitList);
       recyclerView.setAdapter(adapter);
   }
   /* private void initFruits() {
        for (int i = 0; i < 4; i++) {
            Fruit apple = new Fruit(getRandomLengthName("apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomLengthName("pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("mango"), R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }
    private String getRandomLengthName(String name){
        Random random =new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder =new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }*/
   private void initFruits() {
        for (int i = 0; i < 4; i++) {
            Fruit apple = new Fruit("apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("mango", R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

}