package com.test.scrolltransptoolbar;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/8/31.
 * 解决连续点击多次出现多次Toast的现象
 */

public class ToastUtil {
    private static Toast toast;
    private static String oldMsg;
    private static long time;

//
//
//
//    public static void showToast(Context context, String content) {
//        if (toast == null) {
//            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
//        } else {
//            toast.setText(content);
//        }
//        toast.show();
//    }
//
//    public static void showToast(Context context, String msg, int duration) {
//        if (!msg.equals(oldMsg)) { // 当显示的内容不一样时，即断定为不是同一个Toast
//            Toast.makeText(context, msg, duration).show();
//            time = System.currentTimeMillis();
//        } else {
//            // 显示内容一样时，只有间隔时间大于2秒时才显示
//            if (System.currentTimeMillis() - time > 3000) {
//                Toast.makeText(context, msg, duration).show();
//                time = System.currentTimeMillis();
//            }
//        }
//        oldMsg = msg;
//    }
//
//
//    public static void showToast(int message) {
//        showToast(message, Toast.LENGTH_SHORT, 0);
//    }
//
//    public static void showToast(String message) {
//        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
//    }
//
//    public static void showToast(int message, int icon) {
//        showToast(message, Toast.LENGTH_SHORT, icon);
//    }
//
//    public static void showToast(String message, int icon) {
//        showToast(message, Toast.LENGTH_SHORT, icon, Gravity.BOTTOM);
//    }
//
//    public static void showToastShort(int message) {
//        showToast(message, Toast.LENGTH_SHORT, 0);
//    }
//
//    public static void showToastShort(String message) {
//        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
//    }
//
//    public static void showToastShort(int message, Object... args) {
//        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
//    }
//
//    public static void showToast(int message, int duration, int icon) {
//        showToast(message, duration, icon, Gravity.BOTTOM);
//    }
//
//    public static void showToast(int message, int duration, int icon,
//                                 int gravity) {
//        showToast(context.getString(message), duration, icon, gravity);
//    }
//
//    public static void showToast(int message, int duration, int icon,
//                                 int gravity, Object... args) {
//        showToast(context.getString(message, args), duration, icon, gravity);
//    }
//
//    public static void showToast(String message, int duration, int icon,
//                                 int gravity) {
//        if (message != null && !message.equalsIgnoreCase("")) {
//            showToast(context, message);
//        }
//
//    }

}