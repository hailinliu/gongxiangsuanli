package it.mbkj.login.presenter


import android.widget.Toast
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response

import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.utils.GSonUtil
import it.mbkj.login.activity.LoginActivity
import it.mbkj.login.bean.LoginBean
import it.mbkj.login.bean.LoginBean1


class LoginActivityPresenter:BasePresenter<LoginActivity>() {
    fun login(phone:String,password:String){
        OkGo.post<String>(HttpConfig.BASE_URL+"/read/login")
            .params("phone",phone)
            .params("password",password)
            .execute(object :StringCallback(){
                override fun onSuccess(s: Response<String>?) {
                    if(s!!.body().contains("\"code\":1")){
                        var bean=  GSonUtil.GsonToBean(s!!.body(),LoginBean::class.java)
                        (mView as LoginActivity).setData(bean)
                    }else{
                        var bean=  GSonUtil.GsonToBean(s!!.body(), LoginBean1::class.java)
                        //ToastUtil.showShort(bean.msg)
                       Toast.makeText(context,bean.msg,Toast.LENGTH_SHORT).show()
                       // (mView as LoginActivity).setData(bean)
                    }

                   //ToastUtil.showShort("s:"+bean.error_code)
                }

                override fun onError(response: Response<String>?) {
                   // response!!.body()
                }
            }

            )

    }
}



