package com.tintuc.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tintuc.adapter.MenuAdapter;
import com.tintuc.entity.MenuEntity;
import com.tintuc.interfaces.AdapterListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imgMenu;

    private RecyclerView rvMenu;

    private MenuAdapter menuAdapter;

    private List<MenuEntity> menuEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuEntity menuThoiSu = new MenuEntity();
        menuThoiSu.setId(1);
        menuThoiSu.setTitle("Thời sự");
        menuThoiSu.setSelected(true);

        MenuEntity menuTheThao = new MenuEntity();
        menuTheThao.setId(2);
        menuTheThao.setTitle("Thể thao");

        MenuEntity menuKinhTe = new MenuEntity();
        menuKinhTe.setId(3);
        menuKinhTe.setTitle("Kinh tế");

        MenuEntity menuChinhTri = new MenuEntity();
        menuChinhTri.setId(4);
        menuChinhTri.setTitle("Chính trị");

        menuEntities.add(menuThoiSu);
        menuEntities.add(menuTheThao);
        menuEntities.add(menuKinhTe);
        menuEntities.add(menuChinhTri);

        imgMenu = (ImageView) findViewById(R.id.img_menu);
        // configure the SlidingMenu
        final SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen._10sdp);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen._60sdp);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.layout_menu);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.toggle();
            }
        });

        menuAdapter = new MenuAdapter(menuEntities, new AdapterListener() {
            @Override
            public void onItemClickListener(Object o, int pos, RecyclerView.ViewHolder holder) {
                for (int i = 0;i < menuEntities.size();++i) {
                    MenuEntity entity = menuEntities.get(i);
                    entity.setSelected(false);
                }
                MenuEntity menuEntity = (MenuEntity) o;
                menuEntity.setSelected(true);
                menuAdapter.notifyDataSetChanged();
                menu.toggle();
            }
        });

        rvMenu = (RecyclerView) menu.findViewById(R.id.rv_menu);

        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        rvMenu.setItemAnimator(new DefaultItemAnimator());
        rvMenu.setAdapter(menuAdapter);
    }
}
