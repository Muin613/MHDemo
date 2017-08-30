package com.mh.mhdemo.MHSkin.Manager;

import com.mh.mhdemo.MHSkin.Interface.IMHSkinUpdate;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * Created by Administrator on 2017/8/29.
 */

public class MHSkinUpdateManager {

    private LinkedList<IMHSkinUpdate> skinList;

    private MHSkinUpdateManager() {
        skinList = new LinkedList<>();
    }

    public static MHSkinUpdateManager newInstance() {
        return Instance.instance;
    }

    public void bindAct(IMHSkinUpdate skinUpdate) {
        if (!skinList.contains(skinUpdate))
            skinList.add(skinUpdate);
    }

    public void notifySkinUpdate(String tag,Object skinRes ){
        Iterator<IMHSkinUpdate> it = skinList.iterator();
        while(it.hasNext()){
            IMHSkinUpdate update=it.next();
            update.updateSkin(tag,skinRes);
        }
    }


    private static class Instance {
        public final static MHSkinUpdateManager instance = new MHSkinUpdateManager();
    }
}
