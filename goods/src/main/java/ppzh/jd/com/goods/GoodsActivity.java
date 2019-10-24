package ppzh.jd.com.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import ppzh.jd.com.base.CommonFgmAct;

/**
 * Created by shiyagang on 2019/10/23.
 */

public class GoodsActivity extends CommonFgmAct {


    @Override
    public Fragment getContentFragment() {
        return new GoodsFragment();
    }
}
