package it.mbkj.personcenter.activity

import com.alibaba.android.arouter.facade.annotation.Route
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function

import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.base.EditHintUtils
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.RxSchedulersHelper
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.R
import it.mbkj.personcenter.databinding.ActivityMyUpdatePwdBinding
import it.mbkj.personcenter.presenter.UpdateDealPasswordActivityPresenter
import java.util.concurrent.TimeUnit

@Route(path = ArouterAddress.UPDATEDEALPASSWORDACTIVITY)
class UpdateDealPasswordActivity:BaseActivity<UpdateDealPasswordActivityPresenter>() {
    private var timerDispo: Disposable? = null
    var binding:ActivityMyUpdatePwdBinding?=null
    override val layoutId: Int
        get() = R.layout.activity_my_update_pwd
    override val presenter: UpdateDealPasswordActivityPresenter
        get() = UpdateDealPasswordActivityPresenter()

    override fun initView() {
        setTitle("修改交易密码")
        binding = getViewDataBinding<ActivityMyUpdatePwdBinding>()
        binding!!.etPass.setHint(EditHintUtils.setHintSizeAndContent("请输入新的交易密码",12,true))
        binding!!.etConfirm.setHint(EditHintUtils.setHintSizeAndContent("请再次确认交易密码",12,true))
        binding!!.etNum.setHint(EditHintUtils.setHintSizeAndContent("请输入验证码",12,true))

    }

    override fun initEvent() {
       // super.initEvent()
        binding!!.tvSure.setOnClickListener {
            SPUtil.get(SPConfig.SESSION_ID,"")?.let { it1 ->
                mPresenter!!.updatePwd(
                    it1,binding!!.etPass.text.toString(),binding!!.etConfirm.text.toString(),binding!!.etNum.text.toString())
            }
        }
        binding!!.tvHuoqu.setOnClickListener {
            SPUtil.get(SPConfig.SESSION_ID,"")?.let { it1 -> mPresenter!!.getCode(it1) }
        }
    }
    override fun initData() {


    }

    fun setUI() {
        timerDispo = Flowable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
            .map(Function <Long,Long> {i:Long->60-i })
            .compose(RxSchedulersHelper.transformer())
            .subscribe { i: Long ->
                binding!!.tvHuoqu.text="获取验证码("+i+")"
                binding!!.tvHuoqu.isClickable=false
                binding!!.tvHuoqu.isEnabled=false
                if (i == 0L) {
                    binding!!.tvHuoqu.isClickable=true
                    binding!!.tvHuoqu.isEnabled=true
                    binding!!.tvHuoqu.text = "获取验证码"
                }
            }
    }

    fun setdata(data: String) {
            finish()
    }
}