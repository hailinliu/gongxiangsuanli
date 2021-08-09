package it.mbkj.personcenter.presenter

import android.widget.Toast
import com.lzy.okgo.OkGo

import it.mbkj.lib.base.BaseBean
import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.base.ILoadBind
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.activity.AboutUsActivity
import it.mbkj.personcenter.activity.QianDaoActivity
import it.mbkj.personcenter.bean.ContentBean

class AboutUsActivityPresenter:BasePresenter<AboutUsActivity>() {
    fun aboutUs(){
        OkGo.post<BaseBean<ContentBean>>(HttpConfig.BASE_URL+"/read/about")
            .params("token", SPUtil.get(SPConfig.SESSION_ID,""))
            .execute(object : CallBackOption<BaseBean<ContentBean>>(){

            }.loadBind(mView as AboutUsActivity).execute( object : ILoadBind<BaseBean<ContentBean>> {
                override fun excute(bean: BaseBean<ContentBean>) {
                    if (bean.code==1){
                        (mView as AboutUsActivity).setUI(bean.data)
                    }else{
                        Toast.makeText(context,bean.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }))
    }

}