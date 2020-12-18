package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<News> dataList;
    public RecyclerAdapter(List<News> articleArrayList) {
        dataList = articleArrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                              viewGroup, int viewType) {
            View view = (View) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.recycler_body, viewGroup, false);
            return new VijestiViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int
            position) {
            VijestiViewHolder vijestiViewHolder = (VijestiViewHolder) viewHolder;
            if(dataList.get(position) != null) {
                vijestiViewHolder.tvTitle.setText(((News) dataList.get(position)).getTitle());
                vijestiViewHolder.tvContent.setText(((News) dataList.get(position)).getDescription());
                Picasso.get().load(String.valueOf(dataList.get(position).getUrlToImage())).resize(100, 100).centerCrop().into(vijestiViewHolder.image);
            }
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    static class VijestiViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        ImageView image;
        public VijestiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.txtTitle);
            tvContent = itemView.findViewById(R.id.txtContent);
            image = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
