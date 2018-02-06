package com.example.vinodkrishnamurthy.info_display_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vinodkrishnamurthy on 2/4/18.
 */

public class GuidesAdapter extends RecyclerView.Adapter<GuidesAdapter.ViewHolder>{

    private List<Guides> guidesList;
    private Context context;

    public GuidesAdapter(List<Guides> guidesList, Context context) {
        this.guidesList = guidesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guide_objects_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Guides guides = guidesList.get(position);
        holder.guide_name.setText(guides.getGuide_name());
        holder.guide_city.setText(guides.getGuide_city());
        holder.guide_state.setText(guides.getGuide_state());
        holder.guide_end_date.setText(guides.getGuide_end_date());

        // Use Picasso API to load the Guide icon
        Picasso.with(context).load(guides.getGuide_icon()).into(holder.guide_icon);

    }

    @Override
    public int getItemCount() {
        return guidesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView guide_name;
        public TextView guide_city;
        public TextView guide_state;
        public TextView guide_end_date;
        public ImageView guide_icon;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            guide_name = (TextView) itemView.findViewById(R.id.guideName);
            guide_city = (TextView) itemView.findViewById(R.id.city);
            guide_state = (TextView) itemView.findViewById(R.id.state);
            guide_end_date = (TextView) itemView.findViewById(R.id.endDate);
            guide_icon = (ImageView) itemView.findViewById(R.id.ImageView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);
        }
    }

}
