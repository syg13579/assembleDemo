package com.rock.assemble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rock.assemble.goods.GoodsDetailActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView jump_goods_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jump_goods_detail = findViewById(R.id.jump_goods_detail);
        initListener();
    }

    private void initListener(){
        jump_goods_detail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jump_goods_detail:
                toGoodsDetail();
                break;
        }
    }

    /**
     * 去商品详情页
     */
    private void toGoodsDetail(){
        Intent intent = new Intent(getApplicationContext(), GoodsDetailActivity.class);
        startActivity(intent);
    }
}
