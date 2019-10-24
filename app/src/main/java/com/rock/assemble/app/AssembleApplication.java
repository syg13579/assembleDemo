package com.rock.assemble.app;

import android.app.Application;

import ppzh.jd.com.base.application.AppConfig;
import ppzh.jd.com.base.application.BaseApp;

/**
 * Created by shiyagang on 2019/10/23.
 */

public class AssembleApplication extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
        initComponentList();
    }

    @Override
    public void initModuleApp(Application application) {

    }

    @Override
    public void initModuleData(Application application) {

    }

    //初始化组件
    //通过反射初始化
    private void initComponentList(){
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApp baseApp = (BaseApp) clazz.newInstance();
                baseApp.initModuleApp(this);
                baseApp.initModuleData(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
