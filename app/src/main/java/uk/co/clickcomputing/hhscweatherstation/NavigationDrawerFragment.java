package uk.co.clickcomputing.hhscweatherstation;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawerFragment extends android.support.v4.app.Fragment{

    public static final String PREF_FILE_NAME = "testPref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private RecyclerView mRecyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private Boolean mUserLearnedDrawer;
    private Boolean mFromSavedInstanceState;
    private DrawerAdapter adapter;
    private static String[] titles;

    public NavigationDrawerFragment() {
        mFromSavedInstanceState = false;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null){
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new DrawerAdapter(getActivity(), getData());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(new DrawerAdapter.RecyclerTouchListerner(getActivity(), mRecyclerView, new DrawerAdapter.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                if (position >= 1){
                    Toast.makeText(getActivity(), "Postion greater than 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                if(titles != null) {
                    Toast.makeText(getActivity(), "Tap to view " + titles[position], Toast.LENGTH_SHORT).show();
                }
            }

        }));
        return layout;
    }

    public static List<DrawerRow> getData(){
        List<DrawerRow> data = new ArrayList<>();
        int icons[] = {R.drawable.ic_dashboard,
                R.drawable.ic_speed,
                R.drawable.ic_compass,
                R.drawable.ic_rain,
                R.drawable.ic_preasure,
                R.drawable.ic_temperature,
                R.drawable.ic_humidity};
        titles = new String[]{"DashBoard",
                "Wind Speed",
                "Wind Direction",
                "Rain",
                "Barometric Preasure",
                "Temperature",
                "Humidity"};
        for (int i = 0 ; i < titles.length && i < icons.length; i++){
            DrawerRow current = new DrawerRow();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }

        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClosed){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public void saveToPreferences(Context context, String prefName, String prefValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putString(prefName, prefValue);
        mEditor.apply();
    }

    public static String readFromPreferences(Context context, String prefName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName,defaultValue);
    }
}
