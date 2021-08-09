package it.mbkj.personcenter.presenter

import com.lzy.okgo.OkGo
import it.mbkj.lib.base.BaseBean
import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.base.ILoadBind
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.http.SPConfig
import it.mbkj.personcenter.activity.JiLiGuanggaoActivity
import it.mbkj.personcenter.activity.QianDaoActivity

class JiLiGuanggaoActivityPresenter:BasePresenter<JiLiGuanggaoActivity>() {
    /*fun qiandao(){
        OkGo.post<BaseBean<String>>(HttpConfig.BASE_URL+"/read/me/signing")
            .params("token", SPUtil.get(SPConfig.SESSION_ID,""))
            .execute(object : CallBackOption<BaseBean<String>>(){

            }.loadBind(mView as JiLiGuanggaoActivity).execute( object : ILoadBind<BaseBean<String>> {
                override fun excute(bean: BaseBean<String>) {
                    if (bean.code==1){
                        ToastUtil.showShort(bean.msg)
                        (mView as JiLiGuanggaoActivity).setdata(bean.msg)
                    }else{
                        ToastUtil.showShort(bean.msg)
                    }
                }
            }))
    }*/
}