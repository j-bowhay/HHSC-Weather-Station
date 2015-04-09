package uk.co.clickcomputing.hhscweatherstation;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DashAdapter extends RecyclerView.Adapter<DashAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    String[] data;

    public DashAdapter(FragmentActivity activity, String[] data) {
        inflater = LayoutInflater.from(activity);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dash_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText(data[position]);
    }

    @Override
    public int getItemCount() { return data.length; }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.dash_text);
        }
    }

    public void updateData(String[] data){
        this.data = data;
        notifyDataSetChanged();
    }
}
