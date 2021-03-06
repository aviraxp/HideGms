package com.iacn.hidegms;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by iAcn on 2016/8/4
 * Emali iAcn0301@foxmail.com
 */

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
       try {
           XposedHelpers.findAndHookMethod("com.google.android.gms.common.GooglePlayServicesUtil", loadPackageParam.classLoader,
                   "getErrorDialog", int.class, Activity.class, int.class, OnCancelListener.class, new XC_MethodReplacement() {
                       @Override
                       protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                           return new Dialog((Context) methodHookParam.args[1]) {
                               @Override
                               public void show() {
                               }
                           };
                       }
                   });
           XposedBridge.log("Hide GMS Success");
       } catch (Throwable t) {
           return;
       }
    }
}
