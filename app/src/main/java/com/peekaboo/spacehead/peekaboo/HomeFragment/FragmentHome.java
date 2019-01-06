package com.peekaboo.spacehead.peekaboo.HomeFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.peekaboo.spacehead.peekaboo.MainActivity;
import com.peekaboo.spacehead.peekaboo.MoviesFragment.RequestMoreMovies;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News.NewsAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News.NewsVO;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoVO;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.People.PeopleVO;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class FragmentHome extends Fragment {

    static AVLoadingIndicatorView loadingIndicatorView;
    RelativeLayout homeView;
    static TextView text;
    ImageView reload_image;
    static Activity activity;

    static PeopleAdapter adapter;
    static NewsAdapter newsAdapter;

    static LinearLayoutManager linearLayoutManager;
    static LinearLayoutManager linearLayoutManager2;

    static LinearLayoutManager newsLayoutManager2;


    static RecyclerView horizontalRecyclerView;
    static RecyclerView horizontalRecyclerView2;
    static RecyclerView newsRecyclerView;

    static ArrayList<NewsVO> moreNewsList = null;

    public static int page=1;

    static NestedScrollView scroll;



    private String request_http="https://api.themoviedb.org/3/person/popular?api_key="+Utility.api_key+"&language=en-US&page=";
    public static String newsHttp="https://newsapi.org/v2/everything?sortBy=popularity&q=hollywood movies&from="+Utility.newsDate()+"&apiKey="+Utility.news_api_key+"&page=";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home,container,false);

        loadingIndicatorView=(AVLoadingIndicatorView)view.findViewById(R.id.avi);
        homeView=(RelativeLayout)view.findViewById(R.id.homeView);
        text=(TextView)view.findViewById(R.id.text);
        reload_image =(ImageView)view.findViewById(R.id.reload_image);

        scroll = (NestedScrollView)view.findViewById(R.id.scrollViewHome);


        horizontalRecyclerView=(RecyclerView)view.findViewById(R.id.horizontal_recyclerView);
        horizontalRecyclerView2=(RecyclerView)view.findViewById(R.id.horizontal_recyclerView1);
        newsRecyclerView=(RecyclerView)view.findViewById(R.id.news_recyclerView);

        activity = (MainActivity)view.getContext();

        linearLayoutManager= new LinearLayoutManager(activity,linearLayoutManager.HORIZONTAL,false);
        linearLayoutManager2= new LinearLayoutManager(activity,linearLayoutManager.HORIZONTAL,false);
        newsLayoutManager2= new LinearLayoutManager(activity,linearLayoutManager.VERTICAL,false);


        reload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload_image.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
                loadingIndicatorView.setVisibility(View.VISIBLE);
                getContent();
            }
        });



        getContent();





        return view;
    }

    public void getContent(){
        loading(true);
        Request request=null;
        NewsRequest newsRequest=null;

        if(Utility.isConnected(getActivity())){

            request= new Request();
            request.execute(request_http+1,request_http+2);



            newsRequest= new NewsRequest();

            newsRequest.execute(newsHttp+page,newsHttp+2);

            page++;

        }else{

            loadingIndicatorView.setVisibility(View.GONE);


            reload_image.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);

            Toast.makeText(getActivity(), "Connection Issue", Toast.LENGTH_SHORT).show();
        }
    }



    public static void loadHorizontalPeople(ArrayList<PeopleVO> people){

        loadingIndicatorView.setVisibility(View.GONE);
        ArrayList<PeopleVO> peopleList = new ArrayList<>();


        for(int i=0;i<people.size();i++){


            PeopleVO peopleVO= new PeopleVO();

            peopleVO.setName(people.get(i).getName());
            peopleVO.setProfilePath(people.get(i).getProfilePath());
            peopleVO.setId(people.get(i).getId());
            peopleVO.setKnownForModel(people.get(i).getKnownForModel());


            peopleList.add(peopleVO);
        }

        adapter= new PeopleAdapter(activity,peopleList);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(adapter);

    }
    public static void loadHorizontalPeople2(ArrayList<PeopleVO> people){

        loadingIndicatorView.setVisibility(View.GONE);
        ArrayList<PeopleVO> peopleList = new ArrayList<>();


        for(int i=0;i<people.size();i++){


            PeopleVO peopleVO= new PeopleVO();

            peopleVO.setName(people.get(i).getName());
            peopleVO.setProfilePath(people.get(i).getProfilePath());
            peopleVO.setId(people.get(i).getId());

            peopleVO.setKnownForModel(people.get(i).getKnownForModel());


            peopleList.add(peopleVO);
        }

        adapter= new PeopleAdapter(activity,peopleList);
        horizontalRecyclerView2.setLayoutManager(linearLayoutManager2);
        horizontalRecyclerView2.setAdapter(adapter);

    }


    public static void loadNews(ArrayList<NewsVO> news){
        loadingIndicatorView.setVisibility(View.GONE);
        final ArrayList<NewsVO> newsList = new ArrayList<>();


        for(int i=0;i<news.size();i++){


            NewsVO newsVO= new NewsVO();

            newsVO.setTitle(news.get(i).getTitle());
            newsVO.setAuthor(news.get(i).getAuthor());
            newsVO.setDescription(news.get(i).getDescription());
            newsVO.setUrl(news.get(i).getUrl());
            newsVO.setPoster(news.get(i).getPoster());
            newsVO.setPublishedDate(news.get(i).getPublishedDate());
            newsVO.setSource(news.get(i).getSource());


            newsList.add(newsVO);
        }



        newsAdapter= new NewsAdapter(activity,newsList);
        newsRecyclerView.setLayoutManager(newsLayoutManager2);
        newsRecyclerView.setAdapter(newsAdapter);







        //Load more


        scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) scroll.getChildAt(scroll.getChildCount() - 1);

                int diff = (view.getBottom() - (scroll.getHeight() + scroll.getScrollY()));

                if (diff == 0) {
                    if(Utility.isOnline()){

                        loadingIndicatorView.setVisibility(View.VISIBLE);

                        page++;




                            RequestMoreNews request=null;
                            String number=String.valueOf(page);

                            request= new RequestMoreNews();
                            request.execute(newsHttp+number);

                            final ArrayList<NewsVO> moreNews = getMoreNewsList();

                            for(int i=0;i<moreNews.size();i++){



                                NewsVO newsVO= new NewsVO();

                                newsVO.setTitle(moreNews.get(i).getTitle());
                                newsVO.setAuthor(moreNews.get(i).getAuthor());
                                newsVO.setDescription(moreNews.get(i).getDescription());
                                newsVO.setUrl(moreNews.get(i).getUrl());
                                newsVO.setPoster(moreNews.get(i).getPoster());
                                newsVO.setPublishedDate(moreNews.get(i).getPublishedDate());
                                newsVO.setSource(moreNews.get(i).getSource());


                                newsList.add(newsVO);
                            }
                            loadingIndicatorView.setVisibility(View.GONE);
                            newsAdapter.notifyItemInserted(newsList.size()-1);
                            newsAdapter.notifyDataSetChanged();


                    }




                }
            }
        });



    }


    public static void loading(boolean b){

        if(b==true){

            loadingIndicatorView.setVisibility(View.VISIBLE);


        }else{

            loadingIndicatorView.setVisibility(View.GONE);

        }
    }


    public static ArrayList<NewsVO> getMoreNewsList(){

        return  moreNewsList;

    }

    public static void   setMoreNewsList(ArrayList<NewsVO> news){
        moreNewsList=null;
        moreNewsList=news;

    }


}
