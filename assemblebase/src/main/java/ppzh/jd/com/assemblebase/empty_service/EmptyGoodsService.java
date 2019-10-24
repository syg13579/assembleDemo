package ppzh.jd.com.assemblebase.empty_service;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ppzh.jd.com.assemblebase.service.IGoodsService;
import ppzh.jd.com.assemblebase.service.ILoginService;


public class EmptyGoodsService implements IGoodsService {


    @Override
    public Fragment newGoodsFragment(Bundle bundle) {
        return null;
    }
}
