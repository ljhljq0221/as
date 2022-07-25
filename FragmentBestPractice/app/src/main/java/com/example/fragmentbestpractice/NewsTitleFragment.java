package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.widget.TextView;


public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<News> mNewsList;
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View view){
                super(view);
                newsTitleText=(TextView) view.findViewById(R.id.news_title);
            }
        }
        public NewsAdapter(List<News>newsList){
            mNewsList=newsList;
        }
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item,parent,false);//加载布局
           final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news =mNewsList.get (holder.getAdapterPosition());
                    if(isTwoPane){
                        //如果是双页模式 刷新newsContentFragment中的内容
                        //getFragmentManager 被弃用了
                        NewsContentFragment newsContentFragment = (NewsContentFragment)
                        getParentFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),
                                news.getContent());
                    }else {
                        //如果是单页模式，直接启动NewsContentActicity
                        NewsContentActivity.actionStart(getActivity(),
                                news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news=mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);
        View view=inflater.inflate(R.layout.news_title_frag,container,
                false);
        RecyclerView newsTitleRecyclerView=(RecyclerView) view.
                findViewById(R.id.news_title_recycle_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter=new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }
    private List<News> getNews(){
        List<News> newsList=new ArrayList<>();
        for(int i=1;i<=50;i++){
            News news=new News();
            news.setTitle("This is news title "+i);
            news.setContent(getRandomLengthContent("This is news context "+i+"."));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content){
        Random random= new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder =new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        requireActivity().getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event.getTargetState() == Lifecycle.State.CREATED){
                    //在这里任你飞翔
                    if(getActivity().findViewById(R.id.news_content_layout)!=null){
                        isTwoPane=true;
                    }else {
                        isTwoPane=false;
                    }

                    getLifecycle().removeObserver(this);  //这里是删除观察者
                }
            }
        });
    }

}
