package it.mbkj.personcenter.presenter

import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response

import it.mbkj.lib.base.*
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.GSonUtil
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.activity.ShiMingActivity
import it.mbkj.personcenter.bean.PersonRenzheng
import it.mbkj.personcenter.bean.PingTaiBean
import it.mbkj.personcenter.fragment.JINBiFragment
import org.json.JSONObject

class ShiMingActivityPresenter:BasePresenter<ShiMingActivity>() {
     fun yanzheng(appid:String,timestamp:String,sign:String,name:String,idcard:String,mobile:String){
            OkGo.post<String>("https://api.shumaidata.com/v4/mobile_three/transfer")
                .params("appid",appid)
                .params("timestamp",timestamp)
                .params("sign",sign)
                .params("name",name)
                .params("idcard",idcard)
                .params("mobile",mobile)
                .execute(/*object : CallBackOption<PersonRenzheng>() {}.loadBind(mView as ShiMingActivity).execute(
                    object : ILoadBind<PersonRenzheng> {
                        override fun excute(bean: PersonRenzheng) {
                             if (bean.code==200){
                                 if(bean.data.result.equals("0")){
                                     (mView as ShiMingActivity).setUI(bean.data)
                                 }else{
                                     Toast.makeText(context,bean.data.desc,Toast.LENGTH_SHORT).show()
                                 }

                            }else{
                                 Toast.makeText(context,bean.msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )*/
                object :StringCallback(){
                    override fun onSuccess(response: Response<String>?) {
                        var json=   JSONObject(response!!.body())
                        //var code=   json.getInt("code")
                        var msg = json.getString("msg")
                        if(json.getInt("code")==200){
                            var data=json.getJSONObject("data")
                            if(data.getString("result").equals("0")){
                                (mView as ShiMingActivity).setUI(msg)
                            }else{
                                Toast.makeText(context,data.getString("desc"),Toast.LENGTH_SHORT).show()
                                var num=   SPUtil.get(SPConfig.NUM,"")
                                if(num!!.toInt()==0){
                                    (mView as ShiMingActivity).finish()
                                }else{
                                    SPUtil.put(SPConfig.NUM,(num!!.toInt()-1).toString())
                                    SPUtil.put(SPConfig.TIME,System.currentTimeMillis().toString())
                                }

                            }

                        }else{
                            Toast.makeText(context,json.getString("msg"), Toast.LENGTH_SHORT).show()
                        }


                  /*  var bean=   GSonUtil.GsonToBean(response!!.toString(),PersonRenzheng::class.java)
                        if (bean.code==200){
                            if(bean.data.result.equals("0")){
                                (mView as ShiMingActivity).setUI(bean.data)
                            }else{

                            }

                        }else{
                            Toast.makeText(context,bean.msg, Toast.LENGTH_SHORT).show()
                        }*/
                    }

                }
                )
    }
    fun getRenzheng(username:String,idcard:String,token:String){
        OkGo.post<NewBaseBeanData>(HttpConfig.BASE_URL+"/read/me/real_one")
            .params("username",username)
            .params("idcard",idcard)
            .params("token",token)
            .execute(object : CallBackOption<NewBaseBeanData>() {}.loadBind(mView as ShiMingActivity).execute(
                object : ILoadBind<NewBaseBeanData> {
                    override fun excute(bean: NewBaseBeanData) {
                        if (bean.code==1){
                            (mView as ShiMingActivity).setData(bean.msg)

                        }else{
                            Toast.makeText(context,bean.msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ))

    }
    fun shiming(result:String,token:String){
        OkGo.post<String>(HttpConfig.BASE_URL+"/read/me/real_two")
            .params("result",result)
            .params("token",token)
            .execute(object : StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    response!!.body()
                }

            })
    }
}