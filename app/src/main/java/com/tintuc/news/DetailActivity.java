package com.tintuc.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tintuc.api.NewsApi;
import com.tintuc.entity.PostEntity;
import com.tintuc.interfaces.HttpCallback;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;

public class DetailActivity extends AppCompatActivity {

    private TextView tvTitle;
    private WebView wvContent;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        PostEntity postEntity = (PostEntity) bundle.getSerializable("post");

        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(postEntity.getTitle());
        wvContent = (WebView) findViewById(R.id.wv_content);

        NewsApi.getPostDetail(this, postEntity.getId(), new HttpCallback() {
            @Override
            public void onSuccess(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            PostEntity postEntity1 = new PostEntity(jsonObject);
                            String data = "<html><head><title></title><style>*{max-width:100%;}</style></head><body>" + postEntity1.getContent() + "</body></html>";
                            wvContent.loadData(data, "text/html; charset=utf-8", "utf-8");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetailActivity.this, "Kết nối mạng bị lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
