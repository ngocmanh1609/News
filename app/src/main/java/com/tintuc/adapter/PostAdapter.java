package com.tintuc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tintuc.entity.PostEntity;
import com.tintuc.news.R;

import java.util.List;

/**
 * Created by Hp on 2/5/2018.
 */

public class PostAdapter extends RecyclerView.Adapter {

    private List<PostEntity> postEntities;

    public PostAdapter(List<PostEntity> postEntities) {
        this.postEntities = postEntities;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_post, null);
        PostViewHolder postViewHolder = new PostViewHolder(v);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PostEntity postEntity = postEntities.get(position);

        PostViewHolder postViewHolder = (PostViewHolder) holder;

        postViewHolder.tvTitle.setText(postEntity.getTitle());
        postViewHolder.tvDesc.setText(postEntity.getDesc());
        Glide.with(postViewHolder.imgThumb.getContext()).load(postEntity.getThumb()).into(postViewHolder.imgThumb);
    }

    private class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView imgThumb;
        TextView tvTitle;
        TextView tvDesc;

        public PostViewHolder(View itemView) {
            super(itemView);
            imgThumb = (ImageView) itemView.findViewById(R.id.img_thumb);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }

    @Override
    public int getItemCount() {
        return postEntities.size();
    }
}
