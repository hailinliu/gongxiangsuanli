package it.mbkj.tradecenter.presenter

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
import it.mbkj.lib.utils.RxBus
import it.mbkj.tradecenter.fragment.XingQiuFragment
import it.mbkj.xingqiu.bean.SuanLiBean
import org.json.JSONObject


class XingQiuFragmentPresenter :BasePresenter<XingQiuFragment>(){
    fun getList(session_id:String){
        OkGo.post<SuanLiBean>(HttpConfig.BASE_URL+"/read/readmill")
            .params("token",session_id)
            .execute(object : CallBackOption<SuanLiBean>() {}.loadBind(mView as XingQiuFragment).execute(
                object : ILoadBind<SuanLiBean> {
                    override fun excute(bean: SuanLiBean) {
                        if(bean.code==2){
                            ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                        }else if (bean.code==1){
                            (mView as XingQiuFragment).setUI(bean.data.list)
                        }else{
                                //ToastUtil.showShort(bean.msg)
                            Toast.makeText(context,bean.msg,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ))

    }
    fun buy(session_id:String,id:Int){
        OkGo.post<String>(HttpConfig.BASE_URL+"/read/readmill/buy")
            .params("token",session_id)
            .params("id",id)
            .execute(/*object : CallBackOption<BaseBean<String>>() {}.loadBind(mView as XingQiuFragment).execute(
                object : ILoadBind<BaseBean<String>> {
                    override fun excute(bean: BaseBean<String>) {

                     *//* if(bean.code==1){
                            Toast.makeText(context,bean.msg,Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context,bean.msg,Toast.LENGTH_SHORT).show()
                        }*//*
                    }
                }
            )*/
            object :StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                 var json=   JSONObject(response!!.body())
                 //var code=   json.getInt("code")
                    var msg = json.getString("msg")
                    RxBus.getDefault().send(1357)
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                    /*if(code==1){

                    }else{

                    }*/

                }

            }
            )
    }
}