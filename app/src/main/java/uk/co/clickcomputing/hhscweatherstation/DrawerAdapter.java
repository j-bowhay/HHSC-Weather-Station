package uk.co.clickcomputing.hhscweatherstation;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder>{

    private LayoutInflater inflator;
    List<DrawerRow> data = Collections.EMPTY_LIST;

    public DrawerAdapter(FragmentActivity activity, List<DrawerRow> data) {
        inflator = LayoutInflater.from(activity);
        this.data = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflator.inflate(R.layout.nav_drawer_row , viewGroup,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        DrawerRow current = data.get(i);
        viewHolder.title.setText(current.title);
        viewHolder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.list_icon);
            title = (TextView) itemView.findViewById(R.id.list_text);
        }
    }
}
