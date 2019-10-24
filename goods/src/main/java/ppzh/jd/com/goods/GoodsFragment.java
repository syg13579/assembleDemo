package ppzh.jd.com.goods;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ppzh.jd.com.assemblebase.ServiceFactory;
import ppzh.jd.com.base.util.ToastUtil;


public class GoodsFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = GoodsFragment.class.getSimpleName();
    public static final int LOGIN_REQUEST_CODE = 2;
    protected FragmentActivity mContext;
    TextView check_login;
    TextView login;

    public static GoodsFragment newInstance() {
        GoodsFragment fragment = new GoodsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentView(), container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }


    public int getContentView() {
        return R.layout.fragment_goods;
    }


    private void initView(View rootView) {
        check_login = rootView.findViewById(R.id.check_login);
        login = rootView.findViewById(R.id.login);
    }

    /**/
    private void initListener() {
        check_login.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.login){
            toLogin();
        }else if (id == R.id.check_login){
            checkLogin();
        }
    }

    /**
     * 检查登录态 ，夸组件调用
     */
    private void checkLogin(){
        boolean isLogin = ServiceFactory.getInstance().getLoginService().isLogin();
        String userId = ServiceFactory.getInstance().getLoginService().getAccountId();
        Log.e(TAG,"是否已经登录："+isLogin+"   登录者账号："+userId);
    }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            boolean isLogin = data.getBooleanExtra("login_status",false);
            String s = "是否登录："+isLogin;
            ToastUtil.show(mContext,s);
            Log.e(TAG,s);
        }
    }
}
