package it.mbkj.personcenter.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import it.mbkj.lib.base.AppManager
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.APKVersionCodeUtils
import it.mbkj.lib.utils.RxBus
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.R
import it.mbkj.personcenter.databinding.ActivityMySetBinding
import it.mbkj.personcenter.presenter.SettingActivityPresenter
import kotlinx.android.synthetic.main.activity_my_set.*

@Route(path = ArouterAddress.SETTINGACTIVITY)
class SettingActivity:BaseActivity<SettingActivityPresenter>() {
    var  binding:ActivityMySetBinding?=null
    override val layoutId: Int
        get() = R.layout.activity_my_set
    override val presenter: SettingActivityPresenter
        get() = SettingActivityPresenter()

    override fun initView() {
        setTitle("设置")
        binding= getViewDataBinding<ActivityMySetBinding>()
        RxBus.getDefault().register(this)
    }

    override fun initEvent() {
        binding!!.llGeren.setOnClickListener {
            ARouter.getInstance().build(ArouterAddress.MYDATUMACTIVITY).navigation()
        }
       /* binding!!.include.ivBack.setOnClickListener {
            RxBus.getDefault().send(1010)
            finish()
        }*/
        binding!!.llXiugai.setOnClickListener {
            ARouter.getInstance().build(ArouterAddress.UPDATELOGINACTIVITY).navigation()
        }
        binding!!.tvTuichu.setOnClickListener {
            SPUtil.put(SPConfig.SESSION_ID,"")
            SPUtil.put(SPConfig.NUM,"")
            SPUtil.put(SPConfig.TIME,"")
            SPUtil.clear()
            RxBus.getDefault().send(1212)
            AppManager.getSingleton().finishAllActivity("MainActivity")
            ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
            //mPresenter!!.getCode(SPUtil.get(SPConfig.SESSION_ID,""))
        }
        binding!!.tvBanben.setText(APKVersionCodeUtils.getVerName(this))
    }
}