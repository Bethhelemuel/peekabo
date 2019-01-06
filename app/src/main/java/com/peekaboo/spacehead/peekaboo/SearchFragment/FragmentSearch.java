package com.peekaboo.spacehead.peekaboo.SearchFragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.peekaboo.spacehead.peekaboo.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Troy on 5/8/2018.
 */

public class FragmentSearch extends Fragment implements SearchLayoutFragment.OnFragmentInteractionListener {

    private static AVLoadingIndicatorView load;
    private FrameLayout fragmentContainer;
    EditText searchBox;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_search,container,false);
        View view2=inflater.inflate(R.layout.layout_fragment_search_result,container,false);

        fragmentContainer=(FrameLayout)view.findViewById(R.id.searchFragment);
        searchBox=(EditText)view.findViewById(R.id.searchBox);
        load=(AVLoadingIndicatorView) view2.findViewById(R.id.load);


        searchBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                fragmentResult(searchBox.getText().toString());
                return false;
            }
        });

        return view;
    }

    public void fragmentResult(String searchText){
        searchLoader(true);

        SearchLayoutFragment fragment= SearchLayoutFragment.newInstance(searchText);
        FragmentManager fragmentManager= getActivity().getFragmentManager();



        FragmentTransaction transaction=fragmentManager.beginTransaction();
//            transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.replace(R.id.searchFragment,fragment);
        transaction.commit();
    }

    public static void searchLoader(Boolean b){

        if(b){

            load.setVisibility(View.VISIBLE);

        }else{

            load.setVisibility(View.GONE);

        }

    }


    @Override
    public void onFragmentInteraction(String uri) {

    }
}
