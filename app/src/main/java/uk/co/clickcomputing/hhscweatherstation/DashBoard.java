package uk.co.clickcomputing.hhscweatherstation;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class DashBoard extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dashboard, container, false);

        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        return layout;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();
            }
        }, 5000);
    }
}
