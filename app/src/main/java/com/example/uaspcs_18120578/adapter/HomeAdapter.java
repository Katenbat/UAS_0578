package com.example.uaspcs_18120578.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uaspcs_18120578.R;
import com.example.uaspcs_18120578.detail_home;
import com.example.uaspcs_18120578.model.Home;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    Context context;
    ArrayList<Home> data;

    public HomeAdapter(Context context, ArrayList<Home> data){
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_riwayat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, final int position) {
         holder.matchTitle.setText(data.get(position).getMatchTitle());
         holder.matchDescription.setText(data.get(position).getMatchDescription());
         holder.awayScore.setText(data.get(position).getAwayScore());
         holder.homeScore.setText(data.get(position).getHomeScore());


        Glide.with(context).load(data.get(position).getImage()).into(holder.gambarMatch);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detail_home.class);
                intent.putExtra("matchId",data.get(position).getMatchId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView matchTitle, awayScore, homeScore, matchDescription;
        ImageView gambarMatch;
        Button btnDetail;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            matchTitle = itemView.findViewById(R.id.titleMatch);
            awayScore = itemView.findViewById((R.id.AwayScore));
            homeScore = itemView.findViewById(R.id.HomeScore);
            matchDescription = itemView.findViewById(R.id.descriptionMatch);
            gambarMatch = itemView.findViewById(R.id.gambarMatch);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }
    }
}
