package be.mygod.unmeteredvpn;

import android.net.VpnService;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class UnmeterifyVpn extends XC_MethodHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod("android.net.VpnService$Builder", lpparam.classLoader, "establish", this);
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) {
        ((VpnService.Builder) param.thisObject).setMetered(false);
    }
}
