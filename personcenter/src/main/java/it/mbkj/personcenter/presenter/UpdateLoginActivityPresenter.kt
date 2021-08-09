package it.mbkj.personcenter.presenter

import com.lzy.okgo.OkGo
import it.mbkj.lib.base.BaseBean
import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.base.ILoadBind
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.personcenter.activity.UpdateLoginActivity

class UpdateLoginActivityPresenter:BasePresenter<UpdateLoginActivity>() {
    fun updataLoginPwd(session_id:String,old_password:String,password:String,password_confirm:String){
        OkGo.post<BaseBean<List<String>>>(HttpConfig.BASE_URL+"/read/me/update_password")
            .params("token",session_id)
            .params("password",password)
            .params("old_password",old_password)
            .params("password_confirm",password_confirm)

            .execute(object : CallBackOption<BaseBean<List<String>>>() {}.loadBind(mView as UpdateLoginActivity).execute(
                object : ILoadBind<BaseBean<List<String>>> {
                    override fun excute(bean: BaseBean<List<String>>) {

                        if(bean.code==1){
                           // ToastUtil.showShort(bean.msg)
                            (mView as UpdateLoginActivity).setUI(bean.msg)
                        }
                    }
                }
            ))

    }
}