package it.mbkj.personcenter.activity

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.RxBus
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.NewPersonBean
import it.mbkj.personcenter.databinding.ActivityMyDataBinding
import it.mbkj.personcenter.presenter.MyDatumActivityPresenter
@Route(path = ArouterAddress.MYDATUMACTIVITY)
class MyDatumActivity:BaseActivity<MyDatumActivityPresenter>() {
    var binding:ActivityMyDataBinding?=null
    override val layoutId: Int
        get() = R.layout.activity_my_data
    override val presenter: MyDatumActivityPresenter
        get() = MyDatumActivityPresenter()

    override fun initView() {
        setTitle("个人资料")
        RxBus.getDefault().register(this)
       binding =  getViewDataBinding<ActivityMyDataBinding>()
        binding!!.include.rightTv.text ="保存"
    }
    override fun initData() {
        SPUtil.get(SPConfig.SESSION_ID,"")?.let { mPresenter!!.getHomePage(it) }
    }

    override fun initEvent() {
        binding!!.include.rightTv.setOnClickListener {
            SPUtil.get(SPConfig.SESSION_ID,"")?.let { it1 -> mPresenter!!.submit(it1,binding!!.etNick.text.toString()) }
        }
    }
    fun setUI(data: NewPersonBean) {
        binding!!.tvPhone.text=data.phone
        binding!!.etNick.setText(data.nickname)
    }

    fun setdata(msg: String) {
        //Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        RxBus.getDefault().send(1357)
        finish()

    }
}