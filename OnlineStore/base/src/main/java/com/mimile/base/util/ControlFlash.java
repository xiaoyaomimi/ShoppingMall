package com.mimile.base.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.widget.Toast;

import com.mimile.base.R;

import static android.hardware.Camera.Parameters.FLASH_MODE_OFF;
import static android.hardware.Camera.Parameters.FLASH_MODE_TORCH;

/**
 * Created by caidongdong on 2016/12/29 16:44
 * email : mircaidong@163.com
 */

public class ControlFlash {
    public Camera camera=null;
    public Camera.Parameters parameters=null;
    private Context context;

    public boolean IsHaveFlash()//判断设备是否有闪光灯
    {
        return !context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
    public ControlFlash(Context context,Camera camera) {//初始化
        parameters=camera.getParameters();
        this.context = context;
        this.camera = camera;
    }
    public void open() {//打开闪光灯
        if(IsHaveFlash())
        {
            //设备不支持闪光灯
            Toast.makeText(context, R.string.flash_not_avalible,Toast.LENGTH_SHORT).show();
            return;
        }
        parameters.setFlashMode(FLASH_MODE_TORCH);//设置闪光灯为手电筒模式
        camera.setParameters(parameters);
        camera.startPreview();
    }
    public void close()//关闭闪光灯
    {
        parameters.setFlashMode(FLASH_MODE_OFF);
        camera.setParameters(parameters);
    }
}
