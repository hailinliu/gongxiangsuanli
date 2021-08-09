package it.mbkj.personcenter.activity

import android.os.Build
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.zzhoujay.richtext.RichText
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.ContentBean
import it.mbkj.personcenter.databinding.ActivityAboutUsBinding
import it.mbkj.personcenter.databinding.ActivityContactUsBinding
import it.mbkj.personcenter.presenter.AboutUsActivityPresenter
@Route(path = ArouterAddress.ABOUTUSACTIVITY)
class AboutUsActivity:BaseActivity<AboutUsActivityPresenter>() {
    var binding:ActivityAboutUsBinding?=null
    override val layoutId: Int
        get() = R.layout.activity_about_us
    override val presenter: AboutUsActivityPresenter
        get() = AboutUsActivityPresenter()

    override fun initView() {
        setTitle("关于我们")
      binding =getViewDataBinding<ActivityAboutUsBinding>()
        mPresenter!!.aboutUs()
       // binding!!.tvContent.setText("")
    }

    fun setUI(data: ContentBean) {
    RichText.from(data.content).into(binding!!.tvContent)

    }

}