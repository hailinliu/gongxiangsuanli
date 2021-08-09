package it.mbkj.lib.utils

import es.dmoral.toasty.Toasty
import io.reactivex.android.schedulers.AndroidSchedulers
import it.mbkj.lib.base.App

class ToastUtil {
    companion object{
        fun showShort(message:CharSequence){
            AndroidSchedulers.mainThread().scheduleDirect( {
                Toasty.normal(App.INSTANCE!!, message).show()
            })
        }
    }
}