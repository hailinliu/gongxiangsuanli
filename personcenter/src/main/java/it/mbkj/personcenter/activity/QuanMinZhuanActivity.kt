package it.mbkj.personcenter.activity

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.personcenter.R

@Route(path = ArouterAddress.QUANMINZHUANACTIVITY)
class QuanMinZhuanActivity:Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personcenter_my)
    }

}