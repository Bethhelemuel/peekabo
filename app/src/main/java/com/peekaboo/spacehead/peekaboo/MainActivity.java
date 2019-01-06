package com.peekaboo.spacehead.peekaboo;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.peekaboo.spacehead.peekaboo.HomeFragment.FragmentHome;
import com.peekaboo.spacehead.peekaboo.MoviesFragment.FragmentMovie;
import com.peekaboo.spacehead.peekaboo.SearchFragment.FragmentSearch;
import com.peekaboo.spacehead.peekaboo.SearchFragment.SearchLayoutFragment;
import com.peekaboo.spacehead.peekaboo.SeriesFragment.FragmentSeries;

public class MainActivity extends AppCompatActivity implements  BottomNavigationViewEx.OnNavigationItemSelectedListener,SearchLayoutFragment.OnFragmentInteractionListener{
//4945275050f30e961d264fe1ff6cce1f

    BottomNavigationViewEx bottomNavigationView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout=(FrameLayout)findViewById(R.id.framelayout);
        loadFragment(new FragmentHome());
       setupBottomNavigation();
    }


    public void setupBottomNavigation(){

        bottomNavigationView=(BottomNavigationViewEx)findViewById(R.id.bottom_nav);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setIconSize(37,37);



        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment=null;

        switch (item.getItemId()){



            case  R.id.nav_home:

                fragment= new FragmentHome();
                break;

            case R.id.nav_latest:

                fragment= new FragmentSeries();
                break;

            case  R.id.nav_movies:

                fragment =new FragmentMovie();
                break;


            case  R.id.nav_search:

                fragment= new FragmentSearch();

                break;

        }

        loadFragment(fragment);
        return true;
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(String uri) {

    }
}


