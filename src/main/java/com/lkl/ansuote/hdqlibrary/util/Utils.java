package com.lkl.ansuote.hdqlibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * Created by huangdongqiang on 15/05/2017.
 */
public class Utils {

    /**
     * 安全转换成 Int 型
     * @param value
     * @param defaultValue
     * @return
     */
    public final static int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }

        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * 将对象序列化到本地
     * @param path
     * @param saveObject
     */
    public static final void saveObject(String path, Object saveObject) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File f = new File(path);
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(saveObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从本地反序列化取出对象
     * @param path
     * @return
     */
    public static final Object restoreObject(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;
        File f = new File(path);
        if (!f.exists()) {
            return null;
        }
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
            return object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    /**
     * 判断app是否安装了
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        return 0 != getAppVersionCode(context, packageName);
    }


    /**
     * 获取App的版本号
     * @param context
     * @param packageName
     * @return
     */
    public static int getAppVersionCode(Context context, String packageName) {
        PackageInfo packageInfo = null;
        int versionCode = 0;
        try {
            if (null != context && null != packageName && !"".equals(packageName)) {
                packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                if(null != packageInfo) {
                    versionCode = packageInfo.versionCode;
                }
            }
        } catch (Exception e) {

        }
        return versionCode;
    }


    /**
     * 通过 drawable id 获取对应图片的路径
     * @param context
     * @param resource
     * @return
     */
    public static String getDrawablePath(Context context, int resource) {
        String filePath = null;
        FileOutputStream fos = null;
        InputStream inputStream = null;
        try {
            //先复制到/data/包名/files/ 目录下
            String dir = context.getFilesDir().getPath();
            if (!TextUtils.isEmpty(dir)) {
                File file = new File(dir, context.getResources().getResourceEntryName(resource) + ".png");
                if (null != file) {
                    if (file.exists()) {
                        return file.getAbsolutePath();
                    } else {
                        inputStream = context.getResources().openRawResource(resource);
                        if (null != inputStream) {
                            fos = new FileOutputStream(file.getAbsoluteFile());

                            byte[] buffer = new byte[8192];
                            int count;
                            // 开始复制Logo图片文件
                            while((count=inputStream.read(buffer)) > 0)
                            {
                                fos.write(buffer, 0, count);
                            }
                            filePath = file.getAbsolutePath();
                        }

                    }


                }
            }
        } catch (Exception e) {

        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return filePath;
    }


    /**
     * 隐藏输入法
     * @param activity
     */
    public static void hideSoftInputFromWindow(Activity activity){
        if (null != activity) {
            Window window = activity.getWindow();
            if (null != window) {
                View decorView = window.getDecorView();
                if (null != decorView) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (null != imm) {
                        //隐藏软键盘 //
                        imm.hideSoftInputFromWindow(decorView.getWindowToken(), 0);
                    }
                }
            }
        }
    }


    /**
     * 输入法反转,原本是打开状态的，则隐藏；原本是关闭状态的，则打开。
     * @param activity
     */
    public static void toggleSoftInputFromWindow(Activity activity) {
        if (null != activity) {
            Window window = activity.getWindow();
            if (null != window) {
                View decorView = window.getDecorView();
                if (null != decorView) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (null != imm) {
                        imm.toggleSoftInputFromWindow(decorView.getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        }
    }

    /**
     * 获取纯颜色的bitmap
     * @param width
     * @param height
     * @param color
     * @return
     */
    public static Bitmap getColormBitmap(int width, int height, int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        if (null != bitmap) {
            bitmap.eraseColor(color);
        }
        return bitmap;
    }


    /**
     * 把对象序列化到本地
     */
    public static void writeObjToFile(Object object, String localPath) {
        if (TextUtils.isEmpty(localPath)) {
            return;
        }

        ObjectOutputStream oos = null;
        try {
            //目录不存在则创建目录
            File dir = new File(localPath);
            if (null != dir && !dir.exists()) {
                dir.mkdirs();
            }

            //删除之前的文件
            File file = new File(localPath);
            if (null != file && file.exists()) {
                file.delete();
            }

            //写入新数据
            oos = new ObjectOutputStream(new FileOutputStream(new File(localPath)));
            if (null != oos) {
                oos.writeObject(object);
            }
        } catch (Exception e) {

        } finally {
            try {
                if (null != oos) {
                    oos.close();
                    oos = null;
                }
            }
            catch (Exception e)
            {
            }
        }
    }


    /**
     * 从本地反序列化出对象
     * @param localPath
     * @return
     */
    public static Object readObjFromFile(String localPath) {
        if (TextUtils.isEmpty(localPath)) {
            return null;
        }

        Object obj = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(localPath)));
            if (null != ois) {
                obj = ois.readObject();
            }
        } catch (Exception e) {

        } finally {
            try {
                if (null != ois) {
                    ois.close();
                    ois = null;
                }
            } catch (Exception e) {

            }
        }
        return obj;
    }

    /**
     * 合并数组
     * @param first
     * @param rest
     * @param <T>
     * @return
     */
    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
