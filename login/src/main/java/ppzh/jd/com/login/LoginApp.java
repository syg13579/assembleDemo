package ppzh.jd.com.login;

import android.app.Application;

import ppzh.jd.com.assemblebase.ServiceFactory;
import ppzh.jd.com.base.application.BaseApp;
import ppzh.jd.com.login.service.LoginService;


public class LoginApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setLoginService(new LoginService());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
