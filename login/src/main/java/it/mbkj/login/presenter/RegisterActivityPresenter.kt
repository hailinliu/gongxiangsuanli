package it.mbkj.login.presenter

import com.lzy.okgo.OkGo
import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.base.ILoadBind
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.login.activity.RegisterActivity
import it.mbkj.login.bean.CodeBean
import it.mbkj.login.bean.LoginBean
import it.mbkj.login.bean.RegisterBean

class RegisterActivityPresenter:BasePresenter<RegisterActivity>() {
    fun getCode(phone: String,type:String){
        OkGo.post<CodeBean>(HttpConfig.BASE_URL + "/read/get_code")
            .params("phone", phone)
            .params("type",type)
            .execute(object : CallBackOption<CodeBean>() {}
                .loadBind(mView as RegisterActivity)
                .execute(object : ILoadBind<CodeBean> {
                    override fun excute(bean: CodeBean) {
                        (mView as RegisterActivity).setData(bean)
                    }
                }) /*  new CallBackOption<BaseBean<LoginBean>>() {}
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getData().getGoogleState() == 0) {
                                mView.loginSuccess(httpData.getData());
                            } else {
                                mView.googleCheck(httpData.getData());
                            }
                        })*/
            )
    }
    fun register(phone: String,code:String,invite_code:String,password:String,password_confirm:String){
        OkGo.post<RegisterBean>(HttpConfig.BASE_URL + "/read/register")
            .params("phone", phone)
            .params("code",code)
            .params("invite_code",invite_code)
            .params("password",password)
            .params("password_confirm",password_confirm)
            .execute(object : CallBackOption<RegisterBean>() {}
                .loadBind(mView as RegisterActivity)
                .execute(object : ILoadBind<RegisterBean> {
                    override fun excute(bean: RegisterBean) {
                        (mView as RegisterActivity).setSuccess(bean)
                    }
                }) /*  new CallBackOption<BaseBean<LoginBean>>() {}
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getData().getGoogleState() == 0) {
                                mView.loginSuccess(httpData.getData());
                            } else {
                                mView.googleCheck(httpData.getData());
                            }
                        })*/
            )
    }
}