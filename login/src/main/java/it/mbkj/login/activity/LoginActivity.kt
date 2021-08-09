package it.mbkj.login.activity


import android.graphics.Bitmap
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.github.yoojia.inputs.AndroidNextInputs
import com.github.yoojia.inputs.Input
import com.github.yoojia.inputs.NextInputs
import it.mbkj.lib.base.*
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.*
import it.mbkj.login.R
import it.mbkj.login.bean.LoginBean
import it.mbkj.login.databinding.ActivityLoginBinding
import it.mbkj.login.presenter.LoginActivityPresenter
import java.util.*

@Route(path = ArouterAddress.LOGINACTIVITY)
class LoginActivity:BaseActivity<LoginActivityPresenter>() {
    var binding:ActivityLoginBinding?=null
    var inputs: AndroidNextInputs?=null
    var code:String?=null
    var bitmap:Bitmap?=null
    override val layoutId: Int
        get() = R.layout.activity_login
    override val presenter: LoginActivityPresenter
        get() = LoginActivityPresenter()

    override fun initView() {
        //super.initView()
        ARouter.getInstance().inject(this)
        RxBus.getDefault().register(this)
        binding = getViewDataBinding<ActivityLoginBinding>()
      inputs = ToastNextInputs()
        inputs!!.clear()
        inputs!!.add(binding!!.etPhone,SchemeUtil.notEmpty(binding!!.etPhone))
        inputs!!.add(binding!!.etPwd,SchemeUtil.notEmpty(binding!!.etPwd))
        inputs!!.add(binding!!.etYanzhengma,SchemeUtil.notEmpty(binding!!.etYanzhengma))
      /*  if(!SPUtil.get(SPConfig.PHONE,"").isNullOrEmpty()){
          binding!!.etPhone.text = Editable.Factory.getInstance().newEditable(SPUtil.get(SPConfig.PHONE,""))
        }
        if(!SPUtil.get(SPConfig.PWD,"").isNullOrEmpty()){
            binding!!.etPwd.text = Editable.Factory.getInstance().newEditable(SPUtil.get(SPConfig.PWD,""))
        }*/
        binding!!.etPhone.setHint(EditHintUtils.setHintSizeAndContent("请输入您的手机号",14,true))
        binding!!.etPwd.setHint(EditHintUtils.setHintSizeAndContent("请输入您的密码",14,true))
        binding!!.etYanzhengma.setHint(EditHintUtils.setHintSizeAndContent("请输入验证码",14,true))
        bitmap= CodeUtils.getInstance().createBitmap()
         code = CodeUtils.getInstance().code
        binding!!.image.setImageBitmap(bitmap)
        binding!!.image.setOnClickListener {
                bitmap = CodeUtils.getInstance().createBitmap()
                code = CodeUtils.getInstance().getCode()
                binding!!.image.setImageBitmap(bitmap)
                //Toast.makeText(this, code, Toast.LENGTH_SHORT).show()

        }


    }
 @Subscribe(threadMode = ThreadMode.MAIN,code=1212)
fun Refresh(){
    binding!!.etPwd.setText("")
     binding!!.etPhone.setText("")
     binding!!.etYanzhengma.setText("")
     bitmap = CodeUtils.getInstance().createBitmap()
     code = CodeUtils.getInstance().getCode()
     binding!!.image.setImageBitmap(bitmap)
}
    override fun initData() {

        binding!!.tvBt.setOnClickListener {
            if(inputs!!.test()){
                if(code!!.equals(binding!!.etYanzhengma.text.toString(),true)){
                    mPresenter!!.login(binding!!.etPhone.text.toString(),binding!!.etPwd.text.toString())
                }else{
                    Toast.makeText(this,"验证码错误",Toast.LENGTH_SHORT).show()
                }
            }

        }
        binding!!.tvRegister.setOnClickListener {
            ARouter.getInstance().build(ArouterAddress.REGISTERACTIVITY).navigation()
        }
        binding!!.tvForget.setOnClickListener {
            ARouter.getInstance().build(ArouterAddress.FORGETPWDACTIVITY).navigation()
        }
    /*    binding!!.ivSee.setOnClickListener {
            if(isSee){
                binding!!.ivSee.setImageResource(R.mipmap.login_unsee)
                binding!!.etPwd.transformationMethod= PasswordTransformationMethod.getInstance()
                binding!!.etPwd.setSelection(binding!!.etPwd.text.length)
            }else{
                binding!!.ivSee.setImageResource(R.mipmap.login_see)
                binding!!.etPwd.transformationMethod= HideReturnsTransformationMethod.getInstance()
                binding!!.etPwd.setSelection(binding!!.etPwd.text.length)
            }
            isSee = !isSee
        }*/

    }
        fun setData(bean:LoginBean){
            if(bean.code==1){
              //  ToastUtil.showShort(bean.msg)
                Toast.makeText(this,bean.msg,Toast.LENGTH_SHORT).show()
                SPUtil.put(SPConfig.SESSION_ID,bean.data.token)
                SPUtil.put(SPConfig.PWD,binding!!.etPwd.text.toString())
                SPUtil.put(SPConfig.PHONE,binding!!.etPhone.text.toString())
                RxBus.getDefault().send(1357)

                ARouter.getInstance().build(ArouterAddress.MAINACTIVITY).withString("sessionId",bean.data.token).navigation()
            }else{
               // ARouter.getInstance().build(ArouterAddress.MAINACTIVITY).navigation()
                Toast.makeText(this,bean.msg,Toast.LENGTH_SHORT).show()
            }
        }
    private var oldTime: Long = 0
    override fun onBackPressed() {
        val nowTime = Date().time
        if (nowTime - oldTime <= 2000) {
            AppManager.getSingleton().AppExit(this)
        } else {
            oldTime = nowTime
            Toast.makeText(this, App.INSTANCE!!.getString(R.string.libbaseActivity1),Toast.LENGTH_SHORT).show()

        }
    }
}