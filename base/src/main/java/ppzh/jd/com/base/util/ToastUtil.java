package ppzh.jd.com.base.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast提示工具类
 */
public class ToastUtil {


    /**
     * 显示Toast
     *
     * @param context : 上下文
     * @param msg     ：提示信息
     */
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


}
