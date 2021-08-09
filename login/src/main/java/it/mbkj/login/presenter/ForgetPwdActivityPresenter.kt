package it.mbkj.login.presenter

import android.widget.Toast
import com.lzy.okgo.OkGo
import it.mbkj.lib.base.BaseBean
import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.base.ILoadBind
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.utils.ToastUtil
import it.mbkj.login.activity.ForgetPwdActivity
import it.mbkj.login.bean.CodeBean
import it.mbkj.login.bean.LoginBean

class ForgetPwdActivityPresenter:BasePresenter<ForgetPwdActivity>() {
    fun getCode(phone: String,type:String) =
        OkGo.post<CodeBean>(HttpConfig.BASE_URL + "/read/get_code")
            .params("phone", phone)
            .params("type",type)
            .execute(object : CallBackOption<CodeBean>() {}
                .loadBind(mView as ForgetPwdActivity)
                .execute(object : ILoadBind<CodeBean> {
                    override fun excute(bean: CodeBean) {
                        (mView as ForgetPwdActivity).update(bean)
                    }
                })
            )
    fun forgetPwd(phone: String,password:String,password_confirm:String,code:String){
        OkGo.post<BaseBean<List<String>>>(HttpConfig.BASE_URL + "/read/forget")
            .params("phone", phone)
            .params("password",password)
            .params("password_confirm",password_confirm)
            .params("code",code)
            .execute(object : CallBackOption<BaseBean<List<String>>>() {}
                .loadBind(mView as ForgetPwdActivity)
                .execute(object : ILoadBind<BaseBean<List<String>>> {
                    override fun excute(bean: BaseBean<List<String>>) {
                        ToastUtil.showShort(bean.msg)
                        (mView as ForgetPwdActivity).setUI(bean.code)
                    }
                })
            )
    }
}