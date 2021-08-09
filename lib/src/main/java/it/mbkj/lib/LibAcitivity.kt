package it.mbkj.lib

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.openset.OSETListener
import com.kc.openset.OSETSplash
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.LogUtil
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import kotlinx.android.synthetic.main.activity_lib.*
import java.lang.reflect.Array.get


@Route(path = ArouterAddress.LibAcitivity)
class LibAcitivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        NavigationBarStatusBar(this, true)
        setContentView(R.layout.activity_lib)
        OSETSplash.getInstance().show(this,fl,"A0913ACCB1E3AB7651C53C9CE9EE6F04",object:OSETListener{
            override fun onShow() {

            }

            override fun onError(p0: String?, p1: String?) {
               // LogUtil.toString()
                var token: String? = SPUtil.get(SPConfig.SESSION_ID, "")
                if(TextUtils.isEmpty(token)){
                    ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                }else{
                    ARouter.getInstance().build(ArouterAddress.MAINACTIVITY).withString("sessionId", token).navigation()
                }
                finish()
            }

            override fun onItemError(p0: String?, p1: String?) {
            }


            override fun onClick() {
              /*  var token: String? = SPUtil.get(SPConfig.SESSION_ID, "")
                if(TextUtils.isEmpty(token)){
                    ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                }else{
                    ARouter.getInstance().build(ArouterAddress.MAINACTIVITY).withString("sessionId", token).navigation()
                }
                finish()*/
            }

            override fun onClose() {
                var token:String?  = SPUtil.get(SPConfig.SESSION_ID, "")
                if(TextUtils.isEmpty(token)){
                    ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                }else{
                    ARouter.getInstance().build(ArouterAddress.MAINACTIVITY).withString("sessionId", token).navigation()
                }
                finish()
            }

        })
       // ImageUtil.setOriginImage(R.mipmap.lib_qidong,image)
     /* disposable =
            Flowable.timer(1000, TimeUnit.MILLISECONDS).compose(RxSchedulersHelper.transformer())
                .subscribe { aLong: Long? ->

                }*/

    }

    /**
     * 导航栏，状态栏隐藏
     * @param activity
     */
    fun NavigationBarStatusBar(activity: Activity, hasFocus: Boolean) {
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val decorView = activity.window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

}