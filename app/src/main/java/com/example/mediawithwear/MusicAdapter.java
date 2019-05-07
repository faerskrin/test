package com.example.mediawithwear;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.GroupViewHolder> {
    private List<UrlModel> urlModels;
    private  int selectpos;
    private SetOnClickItem setOnClickItem;

    public MusicAdapter(List<UrlModel> urlModels) {
        this.urlModels = urlModels;
    }

    public void setSetOnClickItem(SetOnClickItem setOnClickItem) {
        this.setOnClickItem = setOnClickItem;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_music,parent,false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
            holder.title.setText(urlModels.get(position).getName());
            if (selectpos == position)
            {
                holder.itemView.setBackgroundColor(Color.parseColor("#fff84f"));
                holder.img.setVisibility(View.VISIBLE);
            }
            else  {
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.img.setVisibility(View.INVISIBLE);
            }
    }

    @Override
    public int getItemCount() {
        return urlModels.size();
    }

    public void setSearch(List<UrlModel> ur) {
        urlModels = new ArrayList<>();
        urlModels.addAll(ur);
        notifyDataSetChanged();
    }


    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img = itemView.findViewById(R.id.image_play);
        TextView title = itemView.findViewById(R.id.rv_title);
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            setOnClickItem.startClick(urlModels.get(getAdapterPosition()),getAdapterPosition());
        }
    }


    public int getSelectpos() {
        return selectpos;
    }

    public void setSelectpos(int selectpos) {
        this.selectpos = selectpos;
    }

    public interface SetOnClickItem {
        void startClick(UrlModel urlModel,int pos);
    }
}
