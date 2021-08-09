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
import it.mbkj.personcenter.activity.MyDatumActivity
import it.mbkj.personcenter.bean.NewPersonBean
import it.mbkj.personcenter.fragment.PersonCenterFragment
import org.json.JSONObject

class MyDatumActivityPresenter:BasePresenter<MyDatumActivity>() {
    fun getHomePage(session_id:String){
        OkGo.post<BaseBean<NewPersonBean>>(HttpConfig.BASE_URL+"/read/me/detail")
            .params("token",session_id)
            .execute(object : CallBackOption<BaseBean<NewPersonBean>>() {}.loadBind(mView as MyDatumActivity).execute(
                object : ILoadBind<BaseBean<NewPersonBean>> {
                    override fun excute(bean: BaseBean<NewPersonBean>) {
                        if(bean.code==2){
                            ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                        }else if(bean.code==1){
                            (mView as MyDatumActivity).setUI(bean.data)
                        }
                    }
                }
            ))
    }
    fun submit(session_id:String,nickname:String){
        OkGo.post<String>(HttpConfig.BASE_URL+"/read/me/update_detail")
            .params("token",session_id)
            .params("nickname",nickname)
            .execute(object : StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var json=   JSONObject(response!!.body())
                    //var code=   json.getInt("code")
                    var msg = json.getString("msg")
                    Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
                    (mView as MyDatumActivity).setdata(msg)
                    /*if(code==1){

                    }else{

                    }*/

                }

            })
    }
}