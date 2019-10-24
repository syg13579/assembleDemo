package ppzh.jd.com.base;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;



/**
 * 基类
 * 封装一系列对fragment操作的api
 */
public abstract class CommonFgmAct extends AppCompatActivity {
    protected AppCompatActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        if (getContentView() !=0){
            setContentView(getContentView());
        }
        optFragment();
        //当前activity加入回退栈
    }

    /**
     * @return 从子类获取布局资源
     */
    public int getContentView() {
        return 0;
    }

    public Fragment getContentFragment() {
        return null;
    }
    /**
     * 处理切片
     */
    public void optFragment() {
        Fragment fragment = getContentFragment();
        if (fragment != null) {
            if (getContentView() == 0) {
                setContentView(R.layout.activity_empty);
            }
            add(fragment);
        }
    }


    public void add(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }

    public void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }


}
