package ppzh.jd.com.assemblebase;


import ppzh.jd.com.assemblebase.empty_service.EmptyGoodsService;
import ppzh.jd.com.assemblebase.empty_service.EmptyLoginService;
import ppzh.jd.com.assemblebase.service.IGoodsService;
import ppzh.jd.com.assemblebase.service.ILoginService;

public class ServiceFactory {

    private ILoginService loginService;
    private IGoodsService goodsService;

    /**
     * 禁止外部创建 ServiceFactory 对象
     */
    private ServiceFactory() {
    }

    /**
     * 通过静态内部类方式实现 ServiceFactory 的单例
     */
    public static ServiceFactory getInstance() {
        return Inner.serviceFactory;
    }

    private static class Inner {
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }


//    ------------------------LoginService------------------------
    /**
     * 接收 Login 组件实现的 Service 实例
     */
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 返回 Login 组件的 Service 实例
     */
    public ILoginService getLoginService() {
        if (loginService == null) {
            loginService = new EmptyLoginService();
        }
        return loginService;
    }

//    ------------------------LoginService------------------------

    /**
     * 接收 Login 组件实现的 Service 实例
     */
    public void setGoodsService(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 返回 Login 组件的 Service 实例
     */
    public IGoodsService getGoodsService() {
        if (goodsService == null) {
            goodsService = new EmptyGoodsService();
        }
        return goodsService;
    }
}
