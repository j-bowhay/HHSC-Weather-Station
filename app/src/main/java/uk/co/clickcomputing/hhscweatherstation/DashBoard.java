package uk.co.clickcomputing.hhscweatherstation;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class DashBoard extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dash_board, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView mRecycleView = (RecyclerView) layout.findViewById(R.id.dashRecycler);
        DashAdapter adapter = new DashAdapter(getActivity(), getData());
        mRecycleView.setAdapter(adapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    private String[] getData() {
        String[] data = {"temperature", "Barometer", "Wind"};
        return data;
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
