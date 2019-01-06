package com.peekaboo.spacehead.peekaboo.Preview.People;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peekaboo.spacehead.peekaboo.Preview.Series.SeriesPreviewActivity;
import com.peekaboo.spacehead.peekaboo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PeopleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PeopleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeopleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String the_title = "param1";
    private static final String the_releaseDate = "param2";
    private static final String the_overView = "param3";
    private static final String the_image = "param4";

    // TODO: Rename and change types of parameters
    private String mTitle;
    private String mReleaseDate;
    private String mOverview;

    private String mImage;

    private OnFragmentInteractionListener mListener;

    public PeopleFragment() {
        // Required empty public constructor
    }

    ImageView sendBack,sImage;
    TextView sTitle,sOverview,sReleaseDate;


    // TODO: Rename and change types and number of parameters
    public static PeopleFragment newInstance(String title, String releaseDate, String overview, String image) {
        PeopleFragment fragment = new PeopleFragment();
        Bundle args = new Bundle();
        args.putString(the_title, title);
        args.putString(the_overView, overview);
        args.putString(the_releaseDate, releaseDate);
        args.putString(the_image, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(the_title);
            mOverview = getArguments().getString(the_overView);
            mReleaseDate = getArguments().getString(the_releaseDate);
            mImage = getArguments().getString(the_image);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_people, container, false);

        sendBack=(ImageView)view.findViewById(R.id.sendBack);
        sImage=(ImageView)view.findViewById(R.id.personImage);
        sTitle=(TextView) view.findViewById(R.id.personTitle);
        sOverview=(TextView) view.findViewById(R.id.personOverview);

        sReleaseDate=(TextView) view.findViewById(R.id.personReleaseDate);





       sTitle.setText(mTitle);
       sOverview.setText(mOverview);
       sReleaseDate.setText(mReleaseDate);

        Glide.with(getActivity()).load("https://image.tmdb.org/t/p/w185" + mImage).into(sImage);




        sendBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBack("Back");
                PeoplePreviewActivity.showOverlay(false);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void sendBack(String sendBackText) {
        if (mListener != null) {
            mListener.onFragmentInteraction(sendBackText);
            PeoplePreviewActivity.showOverlay(false);
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
