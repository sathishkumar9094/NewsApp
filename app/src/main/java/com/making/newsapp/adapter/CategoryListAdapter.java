package com.making.newsapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.making.newsapp.R;
import com.making.newsapp.activity.NewsDetailedActivity;
import com.making.newsapp.dbmodel.apimodel.Artical;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Artical> articals;
    private Activity activity;

    public CategoryListAdapter(Context context, ArrayList<Artical> articals, Activity activity) {
        this.context = context;
        this.articals = articals;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artical artical = articals.get(position);
        holder.titleTv.setText(artical.getTitle());
        holder.SubtitleTv.setText(artical.getDescription());

        Picasso.with(context).load(artical.getUrlToImage()).into(holder.newsIv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: "+"ClickedNewsAdapter" );
                Intent i = new Intent(context, NewsDetailedActivity.class);
                i.putExtra("title", artical.getTitle());
                i.putExtra("content", artical.getContent());
                i.putExtra("desc", artical.getDescription());
                i.putExtra("image", artical.getUrlToImage());
                i.putExtra("url", artical.getUrl());
                i.putExtra("publishedAt",artical.getPublishedAt());
                context.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return (articals == null) ? 0 : articals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv, SubtitleTv;
        ImageView newsIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.idTvnews);
            SubtitleTv = itemView.findViewById(R.id.subTitleTv);
            newsIv = itemView.findViewById(R.id.idIvnews);

        }
    }
}
