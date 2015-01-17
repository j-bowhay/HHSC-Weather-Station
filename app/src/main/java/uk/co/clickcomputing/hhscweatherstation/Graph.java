package uk.co.clickcomputing.hhscweatherstation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Graph extends Fragment{
    ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.graph, container, false);
        image = (ImageView) layout.findViewById(R.id.ivGraph);
        return layout;
    }
}
