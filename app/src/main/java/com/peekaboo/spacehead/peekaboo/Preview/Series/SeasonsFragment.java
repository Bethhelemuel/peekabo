package com.peekaboo.spacehead.peekaboo.Preview.Series;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peekaboo.spacehead.peekaboo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SeasonsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SeasonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeasonsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String the_title = "param1";
    private static final String the_epidoseCount = "param2";
    private static final String the_airDate = "param3";
    private static final String the_overview = "param4";
    private static final String the_image = "param5";

    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mEpisodeCount;
    private String mAirDate;
    private String mOverview;
    private String mImage;

    private OnFragmentInteractionListener mListener;

    public SeasonsFragment() {
        // Required empty public constructor
    }

    ImageView sendBack,sImage;
    TextView sTitle,sOverview,sEpisodeCound,sAirDate;


    // TODO: Rename and change types and number of parameters
    public static SeasonsFragment newInstance(String title, String episodeCount, String airDate, String overview, String image) {
        SeasonsFragment fragment = new SeasonsFragment();
        Bundle args = new Bundle();
        args.putString(the_title, title);
        args.putString(the_epidoseCount, episodeCount);
        args.putString(the_airDate, airDate);
        args.putString(the_overview, overview);
        args.putString(the_image, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(the_title);
            mOverview = getArguments().getString(the_overview);
            mAirDate = getArguments().getString(the_airDate);
            mEpisodeCount = getArguments().getString(the_epidoseCount);
            mImage = getArguments().getString(the_image);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_seasons, container, false);

        sendBack=(ImageView)view.findViewById(R.id.sendBack);
        sImage=(ImageView)view.findViewById(R.id.seasonImage);
        sTitle=(TextView) view.findViewById(R.id.season_title);
        sOverview=(TextView) view.findViewById(R.id.season_overview);
        sAirDate=(TextView) view.findViewById(R.id.season_air_date);
        sEpisodeCound=(TextView) view.findViewById(R.id.season_episode_count);





       sTitle.setText(mTitle);
       sOverview.setText(mOverview);
       sAirDate.setText(mAirDate);
       sEpisodeCound.setText(mEpisodeCount);

        Glide.with(getActivity()).load("https://image.tmdb.org/t/p/w185" + mImage).into(sImage);




        sendBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBack("Back");
                SeriesPreviewActivity.showOverlay(false);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void sendBack(String sendBackText) {
        if (mListener != null) {
            mListener.onFragmentInteraction(sendBackText);
            SeriesPreviewActivity.showOverlay(false);
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
