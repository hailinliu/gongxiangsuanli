package it.mbkj.suanli

import android.app.Application
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.openset.OSETSDK
import it.mbkj.lib.base.App
import it.mbkj.lib.service.CommonService

class BaseApplication: Application() {
    @JvmField
    @Autowired
    var commonService: CommonService? = null
    override fun onCreate() {
        super.onCreate()
        var callback = BaseActivityLifecycleCallbacks()
        App.INSTANCE!!.registerActivityLifecycleCallbacks(callback)
        OSETSDK.getInstance().init(this, "4C9E8FBD4A619AAE")
        if(BuildConfig.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
        }else{
            Thread.setDefaultUncaughtExceptionHandler { t: Thread?, e: Throwable? ->
                println(
                    e
                )
            }
        }
        ARouter.init(this)
        ARouter.getInstance().inject(this)

    }

}