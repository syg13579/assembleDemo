package ppzh.jd.com.assemblebase.empty_service;

import ppzh.jd.com.assemblebase.service.ILoginService;


public class EmptyLoginService implements ILoginService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }

}
