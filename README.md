
#Android组件化实践

# 概述
软件开发进程也是架构的演进过程，就拿Android来说，从最开始的MVC ,MVP  ,MVVP ,再到后来的组件化，插件化，但归根到底一切的一切，都是为了项目更好的维护、迭代，降低开发成本。

在一个项目的开发过程中，前期我们可能把所有的功能模块都放到了一个moudle中，这样能够快速的开发，但随着项目壮大，开发人员和功能的增加，就回导致代码越来越臃肿，各个模块之间的耦合越来越重，牵一发而动全身，这个时候为了保证项目质量，我们就需要对项目进行重构。

我们可以根据业务模块进行查分，把不同的业务模块放到不同的moudle中，实现各个业务之间的结构，他们又共同依赖底层公共库，这就是**模块化的概念**，但是当多个模块中涉及到相同功能时代码的耦合又会增加，例如有两个模块都需要视频播放的功能，把视频播放放到两个组件中就会出现代码重复的问题，放到公共库感觉也不是很好，这时候就用组件化来解决这个问题

# 模块化和组件化

## 模块化
具体的业务模块，例如商品详情模块，商品发布模块 ，搜索模块
## 组件化
单一的功能组件，如视频播放组件、分享组件等，每个组件都可以以一个单独的 module 开发，并且可以单独抽出来作为 SDK 对外发布使用

模块化和组件化的思想是一样的，都是对代码进行拆分，但模块化是按功能模块进行查分（业务导向），组件化是按功能模块进行查分（功能导向），模块化的颗粒度更大一些，组件的颗粒度更小一些，**一个项目中模块和组件同时存在也是很常见的，各自负责各自的事情**


![](https://user-gold-cdn.xitu.io/2019/10/25/16e019880ea8d4d0?w=1282&h=1110&f=png&s=163736)

如上图所示 是个组件化项目的基本架构

* **基础库、公共库**：项目所需要的基础操作类，工具类 ，第三方库的引入封装 ，app宿主功能，各个模块，各个组件都依赖这个库
* **组件层**：项目用的功能模块或者业务模块，如：登录模块，视频播放组件，分享组件等
* **应用层**：宿主工程，APP的主项目，APP入口和主架子

# 组件化Demo
地址如下: https://github.com/syg13579/assembleDemo
我根据demo项目从以下几个方面来讲解

* 1：项目分析
* 2：组件application和library动态切换
* 3：组件间的数据传递和方法调用
* 4：组件类（例如：Fragment）的获取,以及夸组件页面跳转和通讯

## 1：项目分析

![](https://user-gold-cdn.xitu.io/2019/10/25/16e019e3ae9a5f68?w=471&h=397&f=png&s=39470)

如上图所示，项目的主要结构


* 应用层：app 项目的主入口
* 组件层：goods login   商品详情页和登录组件
* 基础库层：assemblebase用来各个组件数据和方法交互的 ，base是常用的工具类，各种类库的封装

## 2：组件application和library动态切换
在开发过程中，为了能够实现快速开发，组件能够独立运行就显的特别重要,moudle一般分为两种

* App 插件，id: com.android.application
* Library 插件，id: com.android.library
* 
我们可以通过配置可动态进行application和library的切换，我们在各个组件的gradle.properties文件中配置一个控制切换的变量


![](https://user-gold-cdn.xitu.io/2019/10/25/16e01c92ee9d94ee?w=769&h=414&f=png&s=54462)

然后在build.gradle中就可以通过isRunAlone变量来进行application和library的切换了，主要设计的点有三部分 

* plugin属性的配置
* applicationId的配置
* AndroidManifest的配置
* 

```
if (isRunAlone.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}

android {
    compileSdkVersion 26



    defaultConfig {
        if (isRunAlone.toBoolean()) {
            applicationId "ppzh.jd.com.goods"
        }
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    sourceSets {
        main {
            if (isRunAlone.toBoolean()) {
                manifest.srcFile 'src/main/manifest/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/AndroidManifest.xml'
            }
        }
    }

}
```

如果以上配置就可以实现application和library的切换了

## 3：组件间的数据传递和方法调用
由于主项目、组件之间，组件和组件之间不能直接通过引用进行数据传递和方法调用，那么在开发的过程中怎么进行数据传递和方法调用呢，可以通过「接口」+「实现」的方式进行，

assemblebase基础库就是用来进行数据传递和方法调用的，它被所有组件所依赖，assemblebase提供各个组件对外提供数据和方法调用的抽象service ,同时还有serviceFactory对service进行操作，各个组件在初始化的时候对各自的service进行实现。同时中也会提供所有的 Service 的空实现，以避免引起的空指针异常

就以登录模块为例，对外提供两个数据

```
public interface ILoginService {

    /**
     * 是否已经登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户的 AccountId
     *
     * @return
     */
    String getAccountId();

}
```

相关的serviceFactory类如下，可以通过serviceFactory拉取相关service的实例


```
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

```

在login组件中只需要实现ILoginService，并通过serviceFactory进行设置


```
public class LoginService implements ILoginService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }
}
```

在login的appliction中进行service的设置


```
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
```

但是有这样一个问题：在集成到app中，LoginApp是没有被执行的，这个怎么解决呢，我们可以通过反射进行解决 


```
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
```
如上所示就完成了

## 4：组件类（例如：Fragment）的获取,以及夸组件页面跳转和通讯

fragment的获取也是通过service来完成的

```
public interface IGoodsService {

    /**
     * 创建 GoodsFragment
     * @param bundle
     * @return
     */
    Fragment newGoodsFragment(Bundle bundle);
}
```

相关组件实现该接口就行

各个组件间页面的跳转可以通过[阿里的ARouter](https://github.com/alibaba/ARouter)实现，我是通过设置ComponentName来实现的，**但这种方式好像并没有实现真正的代码隔离**


```
 /**
     *
     * 去登陆
     *
     * 跨组件页面跳转
     */
    private void toLogin(){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(mContext, "ppzh.jd.com.login.LoginActivity"));
        startActivityForResult(intent,LOGIN_REQUEST_CODE);
    }
```

# 总结
通过上面就整体实现了项目组件化，在以后也可以更多的运用组件化来进行项目开发



















