package uk.co.clickcomputing.hhscweatherstation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class Graph extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    String[] graphs;
    int position;
    ImageView image;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.graph, container, false);

        image = (ImageView) layout.findViewById(R.id.ivGraph);
        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.graphSwipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        position = getArguments().getInt("position") - 1;
        graphs = new String[]{"http://www.hillheadsc.org.uk/weewx/images/daywind-Bootstrap.png",
                "http://www.hillheadsc.org.uk/weewx/images/daywinddir-Bootstrap.png",
                "http://www.hillheadsc.org.uk/weewx/images/dayrain-Bootstrap.png",
                "http://www.hillheadsc.org.uk/weewx/images/daybarometer-Bootstrap.png",
                "http://www.hillheadsc.org.uk/weewx/images/daytempchill-Bootstrap.png",
                "http://www.hillheadsc.org.uk/weewx/images/dayhumidity-Bootstrap.png"};

        updateGraph();

        return layout;
    }

    public void updateGraph(){
        Picasso.with(getActivity())
                .load(graphs[position])
                .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                .into(image);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        updateGraph();
    }
}
