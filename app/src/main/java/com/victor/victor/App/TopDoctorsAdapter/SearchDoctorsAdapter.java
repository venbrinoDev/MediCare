package com.victor.victor.App.TopDoctorsAdapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.victor.victor.App.Models.DoctorsInfo;
import com.victor.victor.R;

import java.util.List;
import java.util.zip.Inflater;

public class TopDoctorsAdapter extends RecyclerView.Adapter<TopDoctorsAdapter.ViewHolder> {

    private Context context;
    private List<DoctorsInfo> doctorsInfos;

    public TopDoctorsAdapter(Context context, List<DoctorsInfo> doctorsInfos){
        this.context = context;
        this.doctorsInfos = doctorsInfos;
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
        DoctorsInfo doctorsInfo = doctorsInfos.get(position);

        int doctorsImage = doctorsInfo.getDoctorsImage();
        String doctorsName = doctorsInfo.getDoctorsName();
        String doctorsProfession = doctorsInfo.getDoctorsOccupations();
        String doctorsNumber = doctorsInfo.getDoctorsNumber();
        int whichColor = doctorsInfo.getColor();



        holder.doctorsName.setText(doctorsName);
        holder.doctorsOccupation.setText(doctorsProfession);
        holder.doctorsImage.setImageResource(doctorsImage);
        holder.doctorsNumber.setText(doctorsNumber);
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
