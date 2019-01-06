package com.peekaboo.spacehead.peekaboo.MoviesFragment;

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
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoModel;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Movie.MovieAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoVO;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.GridSpacingItemDecoration;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Troy on 5/8/2018.
 */

public class FragmentMovie extends Fragment {

    private static String request_http="https://api.themoviedb.org/3/discover/movie?api_key="+ Utility.api_key+"&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";

    static AVLoadingIndicatorView loadingIndicatorView;
    static AVLoadingIndicatorView loadingMoreMovies;
    RelativeLayout homeView;
    static TextView text;
    ImageView reload_image;

    static ArrayList<VideoModel> horizontalMovieList;
    static ArrayList<VideoModel> gridMovieList;
    static MovieAdapter gridAdapter;
    static MovieAdapter adapter;
    static RecyclerView horizontalRecyclerView;
    static RecyclerView gridRecyclerView;
    static LinearLayoutManager linearLayoutManager;
    static Activity activity;
    static ArrayList<VideoModel> moreMovieList = null;
    static public int page=2;
    static private int totalRequest=1000;

    static NestedScrollView scroll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_movies,container,false);

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
                getMovies(1);
                getAllMovies(2);
            }
        });


        page=2;
       getMovies(1);
       getAllMovies(2);





        return view;
    }

    public void getMovies(int page){
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


    public void getAllMovies(int page){
//        loading(true);
        RequestAllMovies requestAllMovies=null;
        String number=String.valueOf(page);
        String number2=String.valueOf(page+1);

        if(Utility.isOnline()){

            requestAllMovies= new RequestAllMovies();
            requestAllMovies.execute(request_http+number,request_http+number2);




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


    public static void loadHorizontalMovies(ArrayList<VideoModel> movies){




        loadingIndicatorView.setVisibility(View.GONE);
        ArrayList<VideoVO> movieList1 = new ArrayList<>();


        for(int i=0;i<movies.size();i++){


            VideoVO movieVO1= new VideoVO();

            movieVO1.setPoster(movies.get(i).getPoster());
            movieVO1.setOverview(movies.get(i).getOverview());
            movieVO1.setReleaseDate(movies.get(i).getReleaseDate());
            movieVO1.setTitle(movies.get(i).getTitle());
            movieVO1.setVote(movies.get(i).getVote());
            movieVO1.setId(movies.get(i).getId());

            movieList1.add(movieVO1);
        }

        adapter= new MovieAdapter(activity,movieList1);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(adapter);

    }


    public static void loadAllMovies(ArrayList<VideoModel> movies){



        loadingIndicatorView.setVisibility(View.GONE);
        final ArrayList<VideoVO> movieList2 = new ArrayList<>();

        for(int i=0;i<movies.size();i++){


            VideoVO movieVO= new VideoVO();

            movieVO.setPoster(movies.get(i).getPoster());
            movieVO.setOverview(movies.get(i).getOverview());
            movieVO.setReleaseDate(movies.get(i).getReleaseDate());
            movieVO.setTitle(movies.get(i).getTitle());
            movieVO.setVote(movies.get(i).getVote());
            movieVO.setId(movies.get(i).getId());

            movieList2.add(movieVO);
        }

        gridRecyclerView.setLayoutManager(new GridLayoutManager(activity,3));
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(-5, GridSpacingItemDecoration.HORIZONTAL);
       gridRecyclerView.addItemDecoration(itemDecoration);
        gridAdapter= new MovieAdapter(activity,movieList2);
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

                            final ArrayList<VideoModel> moreMovies = getMoreMovieList();

                            for(int i=0;i<moreMovies.size();i++){



                                VideoVO movieVO= new VideoVO();

                                movieVO.setPoster(moreMovies.get(i).getPoster());
                                movieVO.setOverview(moreMovies.get(i).getOverview());
                                movieVO.setReleaseDate(moreMovies.get(i).getReleaseDate());
                                movieVO.setTitle(moreMovies.get(i).getTitle());
                                movieVO.setVote(moreMovies.get(i).getVote());


                                movieList2.add(movieVO);
                            }
                            loadingMoreMovies.setVisibility(View.GONE);
                            gridAdapter.notifyItemInserted(movieList2.size()-1);
                            gridAdapter.notifyDataSetChanged();
                        }

                    }




                }
            }
        });



    }

    public static ArrayList<VideoModel> getMoreMovieList(){

        return  moreMovieList;

    }

    public static void   setMoreMovieList(ArrayList<VideoModel> movies){
            moreMovieList=null;
            moreMovieList=movies;

    }

}
