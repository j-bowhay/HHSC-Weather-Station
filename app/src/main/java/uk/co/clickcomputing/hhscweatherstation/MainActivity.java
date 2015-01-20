package uk.co.clickcomputing.hhscweatherstation;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class MainActivity extends ActionBarActivity {

    Boolean firstLaunch = Boolean.TRUE;
    android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        manager = getSupportFragmentManager();
        setFragmentDash();

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(android.support.v4.widget.DrawerLayout) findViewById(R.id.drawerLayout), toolbar);
    }

    public void setFragmentDash(){
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        DashBoard dashboard = new DashBoard();
        transaction.replace(R.id.fragmentContainer, dashboard);
        if(!firstLaunch) {
            transaction.addToBackStack(null);
        }else {
            firstLaunch = false;
        }
        transaction.commit();
    }

    public void setFragmentGraph(int position){
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        Graph graph = new Graph();
        transaction.replace(R.id.fragmentContainer, graph);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
