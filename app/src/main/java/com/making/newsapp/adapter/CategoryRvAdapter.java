package com.making.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.making.newsapp.R;
import com.making.newsapp.dbmodel.apimodel.CategoryRVModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRvAdapter extends RecyclerView.Adapter<CategoryRvAdapter.ViewHolder> {


    Context context;
    ArrayList<CategoryRVModal> categoryRVModals;
    CategoryClickInterface categoryClickInterface;

    public CategoryRvAdapter(Context context, ArrayList<CategoryRVModal> categoryRVModals, CategoryClickInterface categoryClickInterface) {
        this.context = context;
        this.categoryRVModals = categoryRVModals;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull

    @Override
    public CategoryRvAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item, parent, false);
        return new CategoryRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull CategoryRvAdapter.ViewHolder holder, int position) {

        CategoryRVModal categoryRVModal=categoryRVModals.get(position);

        holder.categoryTv.setText(categoryRVModal.getCategory());
        Picasso.with(context).load(categoryRVModal.getCategoryImageUrl()).into(holder.categoryIv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (categoryRVModals == null) ? 0 : categoryRVModals.size();
    }

    public interface CategoryClickInterface {
        void onCategoryClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTv;
        ImageView categoryIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTv = itemView.findViewById(R.id.textViewCategory);
            categoryIv = itemView.findViewById(R.id.idIvCategory);


        }
    }
}
