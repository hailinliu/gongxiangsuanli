package it.mbkj.personcenter.activity

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.openset.OSETRewardVideo
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.personcenter.R
import it.mbkj.personcenter.presenter.JiLiGuanggaoActivityPresenter

@Route(path = ArouterAddress.JILIGUANGGAOACTIVITY)
class JiLiGuanggaoActivity:BaseActivity<JiLiGuanggaoActivityPresenter>(){
    override val layoutId: Int
        get() = R.layout.activity_jili
    override val presenter: JiLiGuanggaoActivityPresenter
        get() = JiLiGuanggaoActivityPresenter()

    override fun initView() {

//在OSETVideoListener的onLoad回调里面执行下面方法可显示广告
        //OSETRewardVideo.getInstance().showAd(this)
    }

    fun setdata(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        OSETRewardVideo.getInstance().destory()
        finish()
    }
}