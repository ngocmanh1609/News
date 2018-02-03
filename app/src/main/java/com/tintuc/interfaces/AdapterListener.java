package com.tintuc.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Hp on 2/2/2018.
 */

public interface AdapterListener {
    public void onItemClickListener(Object o, int pos, RecyclerView.ViewHolder holder);
}
