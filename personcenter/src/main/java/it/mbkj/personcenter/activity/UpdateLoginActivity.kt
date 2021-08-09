package it.mbkj.personcenter.activity

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.github.yoojia.inputs.AndroidNextInputs
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.base.EditHintUtils
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import it.mbkj.lib.utils.SchemeUtil
import it.mbkj.lib.utils.ToastNextInputs
import it.mbkj.personcenter.R
import it.mbkj.personcenter.databinding.ActivityMyUpdateLoginPwdBinding
import it.mbkj.personcenter.databinding.ActivityMyUpdatePwdBinding
import it.mbkj.personcenter.presenter.UpdateLoginActivityPresenter


@Route(path = ArouterAddress.UPDATELOGINACTIVITY)
class UpdateLoginActivity:BaseActivity<UpdateLoginActivityPresenter>() {
    var binding:ActivityMyUpdateLoginPwdBinding?=null
    var inputs: AndroidNextInputs?=null
    override val layoutId: Int
        get() = R.layout.activity_my_update_login_pwd
    override val presenter: UpdateLoginActivityPresenter
        get() = UpdateLoginActivityPresenter()

    override fun initView() {
       setTitle("修改登录密码")
         binding =getViewDataBinding<ActivityMyUpdateLoginPwdBinding>()
        inputs = ToastNextInputs()
        inputs!!.clear()
        inputs!!.add(binding!!.etNewPwd, SchemeUtil.notEmpty(binding!!.etNewPwd))
        inputs!!.add(binding!!.etPass, SchemeUtil.notEmpty(binding!!.etPass))
        inputs!!.add(binding!!.etXin, SchemeUtil.notEmpty(binding!!.etXin))
         binding!!.etNewPwd.setHint(EditHintUtils.setHintSizeAndContent("请输入原密码",14,true))
         binding!!.etPass.setHint(EditHintUtils.setHintSizeAndContent("请输入新密码",14,true))
         binding!!.etXin.setHint(EditHintUtils.setHintSizeAndContent("请再次输入新密码",14,true))
    }

    override fun initEvent() {
        binding!!.tvSure.setOnClickListener {
            if(inputs!!.test()){
                SPUtil.get(SPConfig.SESSION_ID,"")?.let { it1 -> mPresenter!!.updataLoginPwd(it1,binding!!.etNewPwd.text.toString(),binding!!.etPass.text.toString(),binding!!.etXin.text.toString()) }
            }

        }
    }

    fun setUI(data: String) {
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show()
        finish()

    }
}