package com.power.nfcdemo.oopalgorithm;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author power
 * @date 2021/9/22 3:44 下午
 * @description:
 */
public class MyContextWrapper extends ContextWrapper {
    Context mBase;
    static final  String TAG = "xOOPAlgorithm";

    MyContextWrapper(Context base) {
        super(base);
        mBase = base;
        Log.e(TAG,"MyContextWrapper.MyContextWrapper() called ");
    }

    static String getPackageCodePathNoCreate(Context context) {
        return context.getFilesDir().getPath() + "base111.apk";
    }

    @Override
    public String getPackageCodePath() {
        Log.e(TAG,"MyContextWrapper.getPackageCodePath() called mBase.getPackageCodePath() = " + mBase.getPackageCodePath());

        // Create the new path
        String originalApkName = getPackageCodePathNoCreate(mBase);
        Log.e(TAG,"MyContextWrapper newpath = " + originalApkName);

        // Check if file already exists
        File f = new File(originalApkName);
        if(f.exists() && !f.isDirectory()) {
            Log.e(TAG,"MyContextWrapper apk exists, returning it = " + originalApkName);
            return originalApkName;
        }

        // Does not exist, we will read it as a resource and write it to new place.
        try {
            Resources res = getResources();
            int id = getResources().getIdentifier("original_apk", "raw", getPackageName());
            InputStream in_s = res.openRawResource(id);
            FileOutputStream out = new FileOutputStream(originalApkName);

            byte[] b = new byte[1024*1024];

            while (true) {
                int readBytes = in_s.read(b);
                if (readBytes < 0) {
                    break;
                }
                out.write(b, 0 ,readBytes);
                Log.e(TAG,"MyContextWrapper succesfully read  = " + readBytes);
            }

            Log.e(TAG,"MyContextWrapper succesfully wrote file  = " + originalApkName);
            out.close();

        } catch (IOException e) {
            Log.e(TAG,"Error: reading resource file", e);
        }

        return originalApkName;
    }
}
