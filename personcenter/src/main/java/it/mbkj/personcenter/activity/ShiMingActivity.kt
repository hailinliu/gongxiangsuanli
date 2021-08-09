package it.mbkj.personcenter.activity

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.github.yoojia.inputs.AndroidNextInputs
import it.mbkj.lib.base.AppManager
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.base.EditHintUtils
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.MD5Utils
import it.mbkj.lib.utils.SPUtil
import it.mbkj.lib.utils.SchemeUtil
import it.mbkj.lib.utils.ToastNextInputs
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.Datap
import it.mbkj.personcenter.databinding.ActivityShimingBinding
import it.mbkj.personcenter.presenter.ShiMingActivityPresenter
@Route(path = ArouterAddress.SHIMINGACTIVITY)
class ShiMingActivity:BaseActivity<ShiMingActivityPresenter>() {
    var binding:ActivityShimingBinding? =null
    var inputs: AndroidNextInputs?=null
    override val layoutId: Int
        get() = R.layout.activity_shiming
    override val presenter: ShiMingActivityPresenter
        get() = ShiMingActivityPresenter()

    override fun initView() {
        setTitle("实名认证")
        ARouter.getInstance().inject(this)
        AppManager.getSingleton().addActivity(this)
        binding=  getViewDataBinding<ActivityShimingBinding>()
        inputs = ToastNextInputs()
        inputs!!.clear()
        inputs!!.add(binding!!.etPass, SchemeUtil.notEmpty(binding!!.etPass))
        inputs!!.add(binding!!.etXin, SchemeUtil.notEmpty(binding!!.etXin))
        binding!!.include.tvTitle.setTextColor(resources.getColor(R.color.lib_view))
        binding!!.include.leftImg.setColorFilter(resources.getColor(R.color.lib_view))
        binding!!.etPass.setHint(EditHintUtils.setHintSizeAndContent("请输入真实姓名",14,true))
        binding!!.etXin.setHint(EditHintUtils.setHintSizeAndContent("请输入身份证号",14,true))
        binding!!.tvSure.setOnClickListener {
            if(inputs!!.test()){
                SPUtil.get(SPConfig.PHONE,"")?.let { it1 ->
                    mPresenter!!.yanzheng("ZdS6tcUDkoWfMIuI",System.currentTimeMillis().toString(),
                        MD5Utils.md5("ZdS6tcUDkoWfMIuI"+"&"+System.currentTimeMillis().toString()+"&"+"ZdS6tcUDkoWfMIuIbGTEvwXHBBe35z6o"),binding!!.etPass.text.toString(),binding!!.etXin.text.toString(),
                        it1
                    )
                }
            }


        }
    }

    fun setUI(data: String) {
        SPUtil.get(SPConfig.SESSION_ID,"")?.let {
            mPresenter!!.getRenzheng(binding!!.etPass.text.toString(),binding!!.etXin.text.toString(),
                it
            )
        }
    }

    override fun initData() {
        //super.initData()
       // SPUtil.get(SPConfig.SESSION_ID,"")?.let { mPresenter!!.shiming("1", it) }
    }
    fun setData(msg: String) {
       Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        ARouter.getInstance().build(ArouterAddress.FACEACTIVITY).withString("idcard",binding!!.etXin.text.toString()).withString("name",binding!!.etPass.text.toString()).navigation()
    }
}