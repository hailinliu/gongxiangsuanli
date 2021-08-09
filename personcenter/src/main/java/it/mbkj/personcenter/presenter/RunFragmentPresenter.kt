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
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.bean.RunBean
import it.mbkj.personcenter.fragment.RunFragment
import org.json.JSONObject

class RunFragmentPresenter:BasePresenter<RunFragment>() {
    fun getList(){
        OkGo.post<BaseBean<RunBean>>(HttpConfig.BASE_URL+"/read/me/myreadmill")
            .params("token", SPUtil.get(SPConfig.SESSION_ID,""))
            .execute(object : CallBackOption<BaseBean<RunBean>>(){

            }.loadBind(mView as RunFragment).execute( object : ILoadBind<BaseBean<RunBean>> {
                override fun excute(bean: BaseBean<RunBean>) {
                    if(bean.code==2){
                        ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                    }else if (bean.code==1){
                        (mView as RunFragment).setUI(bean.data.yunxingzhong)
                    }else{
                        Toast.makeText(context,bean.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }))
    }
    fun getBuy(id:String){
        OkGo.post<String>(HttpConfig.BASE_URL+"/read/me/ad")
            .params("token", SPUtil.get(SPConfig.SESSION_ID,""))
            .params("id",id)
            .execute(object : StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var json=   JSONObject(response!!.body())
                    var msg = json.getString("msg")
                   // Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                }

            })
    }
}