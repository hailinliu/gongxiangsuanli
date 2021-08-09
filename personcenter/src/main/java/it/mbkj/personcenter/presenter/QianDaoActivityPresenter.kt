package it.mbkj.personcenter.presenter

import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseBean
import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.base.ILoadBind
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.RxBus
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.activity.JiLiGuanggaoActivity
import it.mbkj.personcenter.activity.QianDaoActivity
import it.mbkj.personcenter.bean.QianDaoBean
import it.mbkj.personcenter.bean.RunBean
import it.mbkj.personcenter.fragment.OverFragment
import org.json.JSONObject

class QianDaoActivityPresenter:BasePresenter<QianDaoActivity>() {
    fun getList(){
        OkGo.post<BaseBean<QianDaoBean>>(HttpConfig.BASE_URL+"/read/me/sign")
            .params("token", SPUtil.get(SPConfig.SESSION_ID,""))
            .execute(object : CallBackOption<BaseBean<QianDaoBean>>(){

            }.loadBind(mView as QianDaoActivity).execute( object : ILoadBind<BaseBean<QianDaoBean>> {
                override fun excute(bean: BaseBean<QianDaoBean>) {
                    if(bean.code==2){
                        ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                    }else if (bean.code==1){
                        (mView as QianDaoActivity).setUI(bean.data)
                    }else{
                        Toast.makeText(context,bean.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }))
    }
    fun qiandao(){
        OkGo.post<String>(HttpConfig.BASE_URL+"/read/me/signing")
            .params("token", SPUtil.get(SPConfig.SESSION_ID,""))
            .execute(object : StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var json=   JSONObject(response!!.body())
                    //var code=   json.getInt("code")
                    var msg = json.getString("msg")
                    RxBus.getDefault().send(1357)
                    (mView as QianDaoActivity).setdata(msg)
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                    /*if(code==1){

                    }else{

                    }*/

                }

            })
    }
}