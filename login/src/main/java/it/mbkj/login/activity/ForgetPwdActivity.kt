package it.mbkj.login.activity

import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ForegroundColorSpan
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
import it.mbkj.login.databinding.ActivityForgetPwdBinding
import it.mbkj.login.presenter.ForgetPwdActivityPresenter
import java.util.concurrent.TimeUnit

@Route(path = ArouterAddress.FORGETPWDACTIVITY)
class ForgetPwdActivity:BaseActivity<ForgetPwdActivityPresenter>() {
    var binding:ActivityForgetPwdBinding?=null
    private var timerDispo: Disposable? = null
    var inputs: AndroidNextInputs?=null
    var isSee1:Boolean=true
    var isSee2:Boolean=true
    override val layoutId: Int
        get() = R.layout.activity_forget_pwd
    override val presenter: ForgetPwdActivityPresenter
        get() = ForgetPwdActivityPresenter()

    override fun initView() {
        binding= getViewDataBinding<ActivityForgetPwdBinding>()
        RxBus.getDefault().register(this)
        DisposUtil.close(timerDispo)
        inputs = ToastNextInputs()
        inputs!!.clear()
        inputs!!.add(binding!!.etPhone, SchemeUtil.notEmpty(binding!!.etPhone))
        inputs!!.add(binding!!.etPwd, SchemeUtil.notEmpty(binding!!.etPwd))
        inputs!!.add(binding!!.etPwdAgain, SchemeUtil.notEmpty(binding!!.etPwdAgain))
        inputs!!.add(binding!!.etYanzheng, SchemeUtil.notEmpty(binding!!.etYanzheng))
        binding!!.etPhone.setHint(EditHintUtils.setHintSizeAndContent("请输入手机号",14,true))
        binding!!.etPwd.setHint(EditHintUtils.setHintSizeAndContent("请输入密码",14,true))
        binding!!.etPwdAgain.setHint(EditHintUtils.setHintSizeAndContent("请再次输入密码",14,true))
        binding!!.etYanzheng.setHint(EditHintUtils.setHintSizeAndContent("请输入验证码",14,true))
    }

    override fun initEvent() {
        //super.initEvent()
        binding!!.llBack.setOnClickListener {
            RxBus.getDefault().send(1212)
            finish()
        }
        binding!!.tvTime.setOnClickListener {
            if(!TextUtils.isEmpty(binding!!.etPhone.text.toString())){
                mPresenter!!.getCode(binding!!.etPhone.text!!.toString(),"forget")
            }else{
                Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show()
            }
        }
        binding!!.tvSure.setOnClickListener {
            if(inputs!!.test()){
                mPresenter!!.forgetPwd(binding!!.etPhone.text.toString(),binding!!.etPwd.text.toString(),binding!!.etPwdAgain.text.toString(),binding!!.etYanzheng.text.toString())
            }
        }
/*        binding!!.ivSee1.setOnClickListener {
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
        }
        binding!!.ivSee2.setOnClickListener {
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
                //.map(Function<Long, Long> { i: Long -> 60 - i })
                .map(Function<Long, Long> { i: Long -> 60 - i })
                .compose(RxSchedulersHelper.transformer())
                .subscribe { i: Long ->
                    binding!!.tvTime.text="重新获取("+i+")"
                    binding!!.tvTime.isClickable=false
                    binding!!.tvTime.isEnabled=false
                    if (i == 0L) {
                        binding!!.tvTime.isClickable=true
                        binding!!.tvTime.isEnabled=true
                        binding!!.tvTime.text="重新获取"
                    }
                }
        }
       // ToastUtil.showShort(bean.msg)
        Toast.makeText(this,bean.msg,Toast.LENGTH_SHORT).show()
    }

    fun setUI(code: Int) {
        if(code==1){
            RxBus.getDefault().send(1212)
           finish()
        }
    }

    fun update(bean: CodeBean) {
        binding!!.etYanzheng.setText(bean.data.code)
        Toast.makeText(this,bean.msg,Toast.LENGTH_SHORT).show()
        //finish()
    }
}