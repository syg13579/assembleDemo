package ppzh.jd.com.assemblebase.service;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public interface IGoodsService {

    /**
     * 创建 GoodsFragment
     * @param bundle
     * @return
     */
    Fragment newGoodsFragment(Bundle bundle);
}
