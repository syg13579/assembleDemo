package ppzh.jd.com.goods.service;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ppzh.jd.com.assemblebase.service.IGoodsService;
import ppzh.jd.com.goods.GoodsFragment;

/**
 * Created by shiyagang on 2019/10/23.
 */

public class GoodsService implements IGoodsService {
    @Override
    public Fragment newGoodsFragment(Bundle bundle) {
        GoodsFragment goodsFragment = new GoodsFragment();
        if (bundle !=null){
            goodsFragment.setArguments(bundle);
        }
        return goodsFragment;
    }
}
