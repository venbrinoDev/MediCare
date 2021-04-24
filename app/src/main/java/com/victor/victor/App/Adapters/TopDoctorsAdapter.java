package com.victor.victor.App.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.victor.victor.App.Models.SearchDoctors;
import com.victor.victor.App.inteface.ClickItem;
import com.victor.victor.R;

import java.util.List;

public class TopDoctorsAdapter extends RecyclerView.Adapter<TopDoctorsAdapter.ViewHolder> {

    private Context context;
    private List<SearchDoctors> doctorsInfos;
    private ClickItem clickItem;

    public TopDoctorsAdapter(Context context, List<SearchDoctors> doctorsInfos, ClickItem clickItem){
        this.context = context;
        this.doctorsInfos = doctorsInfos;
        this.clickItem = clickItem;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater  layoutInflater= LayoutInflater.from(parent.getContext());
        View showView = layoutInflater.inflate(R.layout.itemview_horizontal,parent,false);
        return new ViewHolder(showView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchDoctors doctorsInfo = doctorsInfos.get(position);

        int doctorsImage = doctorsInfo.getDoctorsImage();
        String doctorsName = doctorsInfo.getDoctorsName();
        String doctorsProfession = doctorsInfo.getDoctorsOccupations();
        String doctorsInfoRating = doctorsInfo.getRating();



        holder.doctorsName.setText(doctorsName);
        holder.doctorsOccupation.setText(doctorsProfession);
        holder.doctorsImage.setImageResource(doctorsImage);
        holder.doctorsNumber.setText(doctorsInfoRating);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickItem !=null){
                    clickItem.clickSearchDoctors(doctorsInfo);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorsInfos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView doctorsImage;
        public TextView doctorsNumber;
        public  TextView doctorsName;
        public  TextView doctorsOccupation;
        public CardView baseBaseBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorsImage = itemView.findViewById(R.id.doctorsImage);
            doctorsNumber = itemView.findViewById(R.id.doctorsNumber);
            doctorsOccupation = itemView.findViewById(R.id.doctorsOccupation);
            doctorsName = itemView.findViewById(R.id.doctorsName);
            baseBaseBackground = itemView.findViewById(R.id.baseBackground);


        }
    }
}
