package it.mbkj.login.activity

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.github.yoojia.inputs.AndroidNextInputs
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function

import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.base.EditHintUtils
import it.mbkj.lib.utils.*
import it.mbkj.login.R
import it.mbkj.login.bean.CodeBean
import it.mbkj.login.bean.LoginBean
import it.mbkj.login.bean.RegisterBean
import it.mbkj.login.databinding.ActivityRegisterBinding
import it.mbkj.login.presenter.RegisterActivityPresenter
import java.util.concurrent.TimeUnit

@Route(path =ArouterAddress.REGISTERACTIVITY)
class RegisterActivity:BaseActivity<RegisterActivityPresenter>() {
    var binding:ActivityRegisterBinding?=null
    private var timerDispo: Disposable? = null
    var inputs: AndroidNextInputs?=null
    override val layoutId: Int
        get() = R.layout.activity_register
    override val presenter: RegisterActivityPresenter
        get() = RegisterActivityPresenter()

    override fun initView() {
       binding= getViewDataBinding<ActivityRegisterBinding>()
        RxBus.getDefault().register(this)
        DisposUtil.close(timerDispo)
        inputs = ToastNextInputs()
        inputs!!.clear()
        inputs!!.add(binding!!.etPhone, SchemeUtil.notEmpty(binding!!.etPhone))
        inputs!!.add(binding!!.etPwd, SchemeUtil.notEmpty(binding!!.etPwd))
        inputs!!.add(binding!!.etPwdAgain, SchemeUtil.notEmpty(binding!!.etPwdAgain))
        inputs!!.add(binding!!.etYanzheng, SchemeUtil.notEmpty(binding!!.etYanzheng))
        inputs!!.add(binding!!.etYaoqing, SchemeUtil.notEmpty(binding!!.etYaoqing))
        binding!!.etPhone.setHint(EditHintUtils.setHintSizeAndContent("请输入手机号",14,true))
        binding!!.etPwd.setHint(EditHintUtils.setHintSizeAndContent("请输入密码",14,true))
        binding!!.etPwdAgain.setHint(EditHintUtils.setHintSizeAndContent("请再次输入密码",14,true))
        binding!!.etYanzheng.setHint(EditHintUtils.setHintSizeAndContent("请输入验证码",14,true))
        binding!!.etYaoqing.setHint(EditHintUtils.setHintSizeAndContent("请输入邀请码",14,true))
    }

    override fun initData() {

    }

    override fun initEvent() {
       binding!!.tvTime.setOnClickListener {
           mPresenter!!.getCode(binding!!.etPhone.text!!.toString(),"register")
       }
        binding!!.tvRegister.setOnClickListener {
            if(inputs!!.test()){
                if(Utils.isFastClick()){
                    mPresenter!!.register(binding!!.etPhone.text!!.toString(),binding!!.etYanzheng.text!!.toString(),
                        binding!!.etYaoqing.text!!.toString(),binding!!.etPwd.text!!.toString(),binding!!.etPwdAgain.text!!.toString())
                }
            }

        }
        binding!!.llBack.setOnClickListener {
            RxBus.getDefault().send(1212)
            finish()
        }
   /*     binding!!.ivSee1.setOnClickListener {
            if(isSee1){
                binding!!.ivSee1.setImageResource(R.mipmap.login_unsee)
                binding!!.etPwd.transformationMethod= PasswordTransformationMethod.getInstance()
                binding!!.etPwd.setSelection(binding!!.etPwd.text.length)
            }else{
                binding!!.ivSee1.setImageResource(R.mipmap.login_see)
                binding!!.etPwd.transformationMethod= HideReturnsTransformationMethod.getInstance()
                binding!!.etPwd.setSelection(binding!!.etPwd.text.length)
            }
            isSee1 = !isSee1
        }*/
    /*    binding!!.ivSee2.setOnClickListener {
            if(isSee2){
                binding!!.ivSee2.setImageResource(R.mipmap.login_unsee)
                binding!!.etPwd.transformationMethod= PasswordTransformationMethod.getInstance()
                binding!!.etPwd.setSelection(binding!!.etPwd.text.length)
            }else{
                binding!!.ivSee2.setImageResource(R.mipmap.login_see)
                binding!!.etPwd.transformationMethod= HideReturnsTransformationMethod.getInstance()
                binding!!.etPwd.setSelection(binding!!.etPwd.text.length)
            }
            isSee2 = !isSee2
        }*/
    }
    fun setData(bean: CodeBean) {
        if(bean.code==1){
            timerDispo = Flowable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
               // .map(Function<Long,Long> {i: Long -> 60 - i})
                .map(Function<Long,Long> {i: Long -> 60 - i})
                .compose(RxSchedulersHelper.transformer())
                .subscribe { i: Long ->
                    binding!!.tvTime.text="重新获取("+i+")"
                    binding!!.tvTime.isClickable=false
                    binding!!.tvTime.isEnabled=false
                    if (i == 0L) {
                        binding!!.tvTime.isClickable=true
                        binding!!.tvTime.isEnabled=true
                    }
                }
            binding!!.etYanzheng.setText(bean.data.code)
        }
        Toast.makeText(this,bean.msg, Toast.LENGTH_SHORT).show()
    }

    fun setSuccess(bean: RegisterBean) {
        Toast.makeText(this,bean.msg,Toast.LENGTH_SHORT).show()
        if(bean.code==1){
            RxBus.getDefault().send(1212)
            finish()
        }

    }
}