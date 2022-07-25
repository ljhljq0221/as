package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   /* private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",};*/
    private List <Fruit> fruitList=new ArrayList<>();

    @Override
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter=new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);

        //ArrayAdapter<String>adapter=new ArrayAdapter<String>(
               // MainActivity.this, android.R.layout.simple_list_item_1,data);
        ListView listView =(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter=new FruitAdapter(MainActivity.
                this,R.layout.fruit_item,fruitList);
        ListView listView =(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                Fruit fruit =fruitList.get(position);//获取用户点击的是哪一个子项
                Toast.makeText(MainActivity.this, fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initFruits(){
        for(int i=0;i<4;i++){
            Fruit apple= new Fruit("apple",R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana= new Fruit("banana",R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange= new Fruit("orange",R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon= new Fruit("watermelon",R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear= new Fruit("pear",R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape= new Fruit("grape",R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple= new Fruit("pineapple",R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry= new Fruit("strawberry",R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry= new Fruit("cherry",R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango= new Fruit("mango",R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }
}