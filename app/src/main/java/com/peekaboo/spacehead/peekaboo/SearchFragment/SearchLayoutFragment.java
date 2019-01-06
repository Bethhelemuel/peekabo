package com.peekaboo.spacehead.peekaboo.SearchFragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.peekaboo.spacehead.peekaboo.MoviesFragment.RequestAllMovies;
import com.peekaboo.spacehead.peekaboo.Preview.Series.SeriesPreviewActivity;
import com.peekaboo.spacehead.peekaboo.R;
import com.peekaboo.spacehead.peekaboo.Utils.GridSpacingItemDecoration;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Movie.MovieAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.Search.SearchAdapter;
import com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.VideoVO;
import com.peekaboo.spacehead.peekaboo.Utils.Model;
import com.peekaboo.spacehead.peekaboo.Utils.Utility;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchLayoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchLayoutFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String searchText = "search Text";


    // TODO: Rename and change types of parameters
    private String mSearchText;
    static RecyclerView mRecyclerView;
    private static Context mContext;

    private OnFragmentInteractionListener mListener;

    public SearchLayoutFragment() {
        // Required empty public constructor
    }


    TextView dummyText;


    // TODO: Rename and change types and number of parameters
    public static SearchLayoutFragment newInstance(String title) {
        SearchLayoutFragment fragment = new SearchLayoutFragment();
        Bundle args = new Bundle();
        args.putString(searchText, title);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchText = getArguments().getString(searchText);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_search_result, container, false);

        mContext=view.getContext();

        mRecyclerView = (RecyclerView)view.findViewById(R.id.searchRecyclerView);

        String request="https://api.themoviedb.org/3/search/multi?api_key="+ Utility.api_key+"&query="+mSearchText+"&language=en-US&page=1&include_adult=false";



            sendRequest(request);


//        sendBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendBack("Back");
//                SeriesPreviewActivity.showOverlay(false);
//            }
//        });


        return view;
    }

    public void sendRequest(String request){
        Toast.makeText(getActivity(), mSearchText, Toast.LENGTH_SHORT).show();
        Request requestResults=null;


        if(Utility.isOnline()){

            requestResults= new Request();
            requestResults.execute(request);


        }else{

            Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();
        }

    }

    public static void loadItems(ArrayList<Model> searchList){

        if(searchList == null){
            FragmentSearch.searchLoader(true);
            Toast.makeText(mContext, "is null", Toast.LENGTH_SHORT).show();
        }else{

            FragmentSearch.searchLoader(false);


            ArrayList<Model> movieList1 = new ArrayList<>();

//
//        for(int i=0;i<searchList.size();i++){
//
//
//            VideoVO movieVO1= new VideoVO();
//
//            movieVO1.setPoster(searchList.get(i).getPoster());
//            movieVO1.setOverview(movies.get(i).getOverview());
//            movieVO1.setReleaseDate(movies.get(i).getReleaseDate());
//            movieVO1.setTitle(movies.get(i).getTitle());
//            movieVO1.setVote(movies.get(i).getVote());
//            movieVO1.setId(movies.get(i).getId());
//
//            movieList1.add(movieVO1);
//        }

            SearchAdapter adapter = new SearchAdapter(searchList,mContext);

            mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
            GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(-5, GridSpacingItemDecoration.HORIZONTAL);
            mRecyclerView.addItemDecoration(itemDecoration);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
            
        }

  

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void sendBack(String sendBackText) {
        if (mListener != null) {
            mListener.onFragmentInteraction(sendBackText);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String uri);
    }
}
