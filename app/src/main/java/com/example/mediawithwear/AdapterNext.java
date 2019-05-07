package com.example.mediawithwear;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterNext extends RecyclerView.Adapter<AdapterNext.GroupViewHolder> {

    List<Content> cont;


    public AdapterNext(List<Content> cont) {
        this.cont = cont;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_next,parent,false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
            holder.text.setText(cont.get(position).getNazv());
    }

    @Override
    public int getItemCount() {
        return cont.size();
    }

    public void setSearch(List<Content> contents) {
        cont = new ArrayList<>();
        cont.addAll(contents);
        notifyDataSetChanged();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView text=  itemView.findViewById(R.id.next_text);
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
