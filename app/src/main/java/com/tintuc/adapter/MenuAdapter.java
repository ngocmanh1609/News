package com.tintuc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tintuc.entity.MenuEntity;
import com.tintuc.interfaces.AdapterListener;
import com.tintuc.news.R;

import java.util.List;

/**
 * Created by Hp on 2/2/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter {

    private AdapterListener listener;
    private List<MenuEntity> menuEntities;

    public MenuAdapter(List<MenuEntity> menuEntityList, AdapterListener listener) {
        this.listener = listener;
        this.menuEntities = menuEntityList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_menu, null);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MenuViewHolder menuViewHolder = (MenuViewHolder) holder;
        final MenuEntity menuEntity = menuEntities.get(position);

        String title = menuEntity.getTitle();
        menuViewHolder.tvMenu.setText(title);

        if (menuEntity.isSelected()) {
            menuViewHolder.rltItemMenu.setBackgroundResource(R.color.colorPrimary);
        } else {
            menuViewHolder.rltItemMenu.setBackgroundResource(android.R.color.white);
        }

        menuViewHolder.rltItemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickListener(menuEntity, position, menuViewHolder);
            }
        });
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rltItemMenu;
        TextView tvMenu;
        public MenuViewHolder(View itemView) {
            super(itemView);
            rltItemMenu = (RelativeLayout) itemView.findViewById(R.id.rlt_item_menu);
            tvMenu = (TextView) itemView.findViewById(R.id.tv_menu);
        }
    }

    @Override
    public int getItemCount() {
        return menuEntities.size();
    }
}
