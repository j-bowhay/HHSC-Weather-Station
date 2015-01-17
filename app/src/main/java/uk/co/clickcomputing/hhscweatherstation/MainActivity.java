package uk.co.clickcomputing.hhscweatherstation;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
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
        transaction.commit();
    }

    public void setFragmentGraph(int position){
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        Graph graph = new Graph();
        transaction.replace(R.id.fragmentContainer, graph);
        transaction.commit();
    }

}
