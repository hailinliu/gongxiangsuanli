package it.mbkj.lib.utils

import android.widget.Toast
import com.github.yoojia.inputs.Input
import com.github.yoojia.inputs.MessageDisplay
import it.mbkj.lib.base.App

class ToastMessageDisplay:MessageDisplay {
    fun ToastMessageDisplay() {}
    override fun show(input: Input?, message: String?) {
        Toast.makeText(App.INSTANCE!!.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}