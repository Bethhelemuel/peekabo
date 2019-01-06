package com.peekaboo.spacehead.peekaboo.SeriesFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
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
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.GridSpacingItemDecoration;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Series.SeriesAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoVO;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class FragmentSeries extends Fragment {

    private static String request_http="https://api.themoviedb.org/3/discover/tv?api_key="+ Utility.api_key+"&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";

    static AVLoadingIndicatorView loadingIndicatorView;
    static AVLoadingIndicatorView loadingMoreMovies;
    RelativeLayout homeView;
    static TextView text;
    ImageView reload_image;

    static ArrayList<VideoModel> horizontalMovieList;
    static ArrayList<VideoModel> gridMovieList;
    static SeriesAdapter gridAdapter;
    static SeriesAdapter adapter;
    static RecyclerView horizontalRecyclerView;
    static RecyclerView gridRecyclerView;
    static LinearLayoutManager linearLayoutManager;
    static Activity activity;
    static ArrayList<VideoModel> moreSeriesList = null;
    static public int page=2;
    static private int totalRequest=1000;

    static NestedScrollView scroll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_series,container,false);

        loadingIndicatorView=(AVLoadingIndicatorView)view.findViewById(R.id.avi);
        loadingMoreMovies=(AVLoadingIndicatorView)view.findViewById(R.id.avi_more);
        homeView=(RelativeLayout)view.findViewById(R.id.homeView);
        text=(TextView)view.findViewById(R.id.text);
        reload_image =(ImageView)view.findViewById(R.id.reload_image);
        horizontalRecyclerView=(RecyclerView)view.findViewById(R.id.horizontal_recyclerView);
        gridRecyclerView=(RecyclerView)view.findViewById(R.id.grid_recyclerView);
        gridRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4));
        activity = (MainActivity)view.getContext();
        linearLayoutManager= new LinearLayoutManager(activity,linearLayoutManager.HORIZONTAL,false);

         scroll = (NestedScrollView)view.findViewById(R.id.myScroll);


        reload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload_image.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
                loadingIndicatorView.setVisibility(View.VISIBLE);
                getSeries(1);
                getAllSeries(2);
            }
        });


        page=2;
       getSeries(1);
       getAllSeries(2);





        return view;
    }

    public void getSeries(int page){
//        loading(true);
        Request request=null;
        String number=String.valueOf(page);

        if(Utility.isConnected(getActivity())){

            request= new Request();
            request.execute(request_http+number);




        }else{

            loadingIndicatorView.setVisibility(View.GONE);


            reload_image.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);

            Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();
        }
    }


    public void getAllSeries(int page){
//        loading(true);
        RequestAllSeries requestAllSeries=null;
        String number=String.valueOf(page);
        String number2=String.valueOf(page+1);

        if(Utility.isOnline()){

            requestAllSeries= new RequestAllSeries();
            requestAllSeries.execute(request_http+number,request_http+number2);




        }else{

            loadingIndicatorView.setVisibility(View.GONE);


            reload_image.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);

            Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();
        }
    }



    public static void loadLayout(String s){

        loadingIndicatorView.setVisibility(View.GONE);








    }


//    public static void loading(boolean b){
//
//        if(b==true){
//
//            loadingIndicatorView.setVisibility(View.VISIBLE);
//
//
//
//        }else{
//
//            loadingIndicatorView.setVisibility(View.GONE);
//
//        }
//    }


    public static void loadHorizontalSeries(ArrayList<VideoModel> series){




        loadingIndicatorView.setVisibility(View.GONE);
        ArrayList<VideoVO> movieList1 = new ArrayList<>();


        for(int i=0;i<series.size();i++){


            VideoVO seriesVO1= new VideoVO();

            seriesVO1.setPoster(series.get(i).getPoster());
            seriesVO1.setOverview(series.get(i).getOverview());
            seriesVO1.setReleaseDate(series.get(i).getReleaseDate());
            seriesVO1.setTitle(series.get(i).getTitle());
            seriesVO1.setVote(series.get(i).getVote());
            seriesVO1.setId(series.get(i).getId());

            movieList1.add(seriesVO1);
        }

        adapter= new SeriesAdapter(activity,movieList1);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(adapter);

    }


    public static void loadAllSeries(ArrayList<VideoModel> series){



        loadingIndicatorView.setVisibility(View.GONE);
        final ArrayList<VideoVO> seriesList2 = new ArrayList<>();

        for(int i=0;i<series.size();i++){


            VideoVO seriesVO= new VideoVO();

            seriesVO.setPoster(series.get(i).getPoster());
            seriesVO.setOverview(series.get(i).getOverview());
            seriesVO.setReleaseDate(series.get(i).getReleaseDate());
            seriesVO.setTitle(series.get(i).getTitle());
            seriesVO.setVote(series.get(i).getVote());
            seriesVO.setId(series.get(i).getId());

            seriesList2.add(seriesVO);
        }

        gridRecyclerView.setLayoutManager(new GridLayoutManager(activity,3));
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(-5, GridSpacingItemDecoration.HORIZONTAL);
       gridRecyclerView.addItemDecoration(itemDecoration);
        gridAdapter= new SeriesAdapter(activity,seriesList2);
//
        gridRecyclerView.setAdapter(gridAdapter);

        page++;
        scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) scroll.getChildAt(scroll.getChildCount() - 1);

                int diff = (view.getBottom() - (scroll.getHeight() + scroll
                        .getScrollY()));

                if (diff == 0) {

                    if(Utility.isOnline()){

                        loadingMoreMovies.setVisibility(View.VISIBLE);

                        page++;

                        if(page > totalRequest){


                        }else{


                            RequestMoreMovies request=null;
                            String number=String.valueOf(page);

                            request= new RequestMoreMovies();
                            request.execute(request_http+number);

                            final ArrayList<VideoModel> moreSeries = getMoreSeriesList();

                            for(int i=0;i<moreSeries.size();i++){



                                VideoVO seriesVO= new VideoVO();

                                seriesVO.setPoster(moreSeries.get(i).getPoster());
                                seriesVO.setOverview(moreSeries.get(i).getOverview());
                                seriesVO.setReleaseDate(moreSeries.get(i).getReleaseDate());
                                seriesVO.setTitle(moreSeries.get(i).getTitle());
                                seriesVO.setVote(moreSeries.get(i).getVote());


                                seriesList2.add(seriesVO);
                            }
                            loadingMoreMovies.setVisibility(View.GONE);
                            gridAdapter.notifyItemInserted(seriesList2.size()-1);
                            gridAdapter.notifyDataSetChanged();
                        }

                    }




                }
            }
        });



    }

    public static ArrayList<VideoModel> getMoreSeriesList(){

        return  moreSeriesList;

    }

    public static void   setMoreSeriesList(ArrayList<VideoModel> series){
            moreSeriesList=null;
            moreSeriesList=series;

    }

}
