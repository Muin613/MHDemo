package com.mh.mhdemo.MHSkin;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import com.mh.mhdemo.R;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexClassLoader;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MHSkinManager {
    private Context context;
    private String pkgName;
    private static final String IMAGE_NAME = "mh";
    private static final String COLOR_NAME = "mh_color";
    private static final String COLOR="color";
    public static final String DRAWABLE = ".R$drawable";
    Resources mResource;
    private int resId;
    private static MHSkinManager mhSkinManager;

    public synchronized static MHSkinManager newInstance() {
        if(mhSkinManager==null)
            mhSkinManager=new MHSkinManager();
        return mhSkinManager;
    }

    public void bindContext(Context context) {
        this.context = context;
        MHSkinConfig.newInstance().bindContext(context);
    }

    public void commit(String name) {
        MHSkinConfig.newInstance().commit(context, name);
    }

    //是否有资源并且获取包名
    public boolean hasResource() {
        if (MHSkinConfig.newInstance().hasResource()) {
            Map<String, String> hasMap = new HashMap<>();
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(MHSkinConfig.newInstance().getRealFilePath(), PackageManager.GET_ACTIVITIES);
            if (null != info) {
                ApplicationInfo appInfo = info.applicationInfo;
                pkgName = appInfo.packageName;
            }
            if (hasMap.size() == 0)
                return false;
            else
                return true;
        }
        return false;
    }


    public void loadAPK() throws Exception {
        File optimizedDirectoryFile = context.getDir("dex", Context.MODE_PRIVATE);
        DexClassLoader dexClassLoader = new DexClassLoader(MHSkinConfig.newInstance().getRealFilePath(), optimizedDirectoryFile.getPath(), null, ClassLoader.getSystemClassLoader());
        Class<?> cls = dexClassLoader.loadClass(pkgName + DRAWABLE);
        Field field = cls.getDeclaredField(IMAGE_NAME);
        resId = field.getInt(R.id.class);
        mResource = getPluginResources(MHSkinConfig.newInstance().getRealFilePath());
    }

    /**
     * @param apkPath
     * @return 得到对应插件的Resource对象
     */
    private Resources getPluginResources(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            //反射调用方法addAssetPath(String path)
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            //将未安装的Apk文件的添加进AssetManager中,第二个参数是apk的路径
            addAssetPath.invoke(assetManager, apkPath);
            Resources superRes = context.getResources();
            Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
            return mResources;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getColorId() {
        if (mResource == null || TextUtils.isEmpty(pkgName))
            return 0;
        int id = mResource.getIdentifier(COLOR_NAME, "color", pkgName);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mResource.getColor(id, null);
        } else
            return mResource.getColor(id);
    }

    public Drawable getDrawable() {
        if (resId == 0 || mResource == null || TextUtils.isEmpty(pkgName))
            return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return mResource.getDrawable(resId, null);
        } else
            return mResource.getDrawable(resId);
    }
}
