package com.rock.assemble.goods;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ppzh.jd.com.assemblebase.ServiceFactory;
import ppzh.jd.com.base.CommonFgmAct;

/**
 * Created by shiyagang on 2019/10/23.
 */

public class GoodsDetailActivity extends CommonFgmAct {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment getContentFragment() {
        return ServiceFactory.getInstance().getGoodsService().newGoodsFragment(null);
    }
}
