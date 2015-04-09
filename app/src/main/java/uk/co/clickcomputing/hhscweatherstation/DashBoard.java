package uk.co.clickcomputing.hhscweatherstation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashBoard extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    DashAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dash_board, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView mRecycleView = (RecyclerView) layout.findViewById(R.id.dashRecycler);
        adapter = new DashAdapter(getActivity(), getData());
        mRecycleView.setAdapter(adapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    private String[] getData() {
        String[] data = {"Outside temperature: 53.4°F;",
                "Barometer: 30.082 inHg;",
                "Wind: 12 mph from 292°;",
                "Rain rate: 0.00 in/hr;",
                "Inside temperature: 70.7°F;"};
        return data;
    }

    @Override
    public void onRefresh() {
        String[] newData = {"This",
                "is",
                "dummy",
                "data"};
        adapter.updateData(newData);
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
