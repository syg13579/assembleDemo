package ppzh.jd.com.goods;

import android.app.Application;

import ppzh.jd.com.assemblebase.ServiceFactory;
import ppzh.jd.com.base.application.BaseApp;
import ppzh.jd.com.goods.service.GoodsService;


public class GoodsApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setGoodsService(new GoodsService());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
