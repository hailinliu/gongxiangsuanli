package it.mbkj.lib.utils

import com.github.yoojia.inputs.AndroidNextInputs

class ToastNextInputs : AndroidNextInputs() {
    init {
        setMessageDisplay(ToastMessageDisplay())
    }
}
