package it.mbkj.personcenter.presenter


import android.widget.Toast
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import it.mbkj.lib.base.*

import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.activity.FaceActivity
import it.mbkj.personcenter.activity.ShiMingActivity
import org.json.JSONObject

class FaceActivityPresenter:BasePresenter<FaceActivity>() {
    fun shiming(result:String,token:String,idcard: String,name: String,hash:String){
        OkGo.post<BaseBean<List<String>>>(HttpConfig.BASE_URL+"/read/me/real_two")
            .params("result",result)
            .params("idcard",idcard)
            .params("name",name)
            .params("hash",hash)
            .params("token",token)
            .execute(object : CallBackOption<BaseBean<List<String>>>() {}.loadBind(mView as FaceActivity).execute(
                object : ILoadBind<BaseBean<List<String>>> {
                    override fun excute(bean: BaseBean<List<String>>) {
                        if (bean.code==1){
                            (mView as FaceActivity).setData(bean.msg)

                        }else{
                            Toast.makeText(context,bean.msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ))
    }
    fun duibi(appid:String,timestamp:String,sign:String,name:String,idcard:String,url:String){
        OkGo.post<String>("https://api.shumaidata.com/v4/face_id_card/compare")
            .params("appid",appid)
            .params("timestamp",timestamp)
            .params("sign",sign)
            .params("name",name)
            .params("idcard",idcard)
            .params("image",url)
            .execute(
                object : StringCallback(){
                    override fun onSuccess(response: Response<String>?) {
                        var json=   JSONObject(response!!.body())
                        //var code=   json.getInt("code")
                        var msg = json.getString("msg")
                        if(json.getInt("code")==200){
                            var data=json.getJSONObject("data")
                            if(data.getDouble("score")>0.45){
                                (mView as FaceActivity).setUI(msg)
                            }else{
                                var num=   SPUtil.get(SPConfig.NUM,"")
                                if(num!!.toInt()==0){
                                    AppManager.getSingleton().finishAllActivity("ShiMingActivity")
                                    (mView as FaceActivity).finish()
                                }else{
                                    SPUtil.put(SPConfig.NUM,(num!!.toInt()-1).toString())
                                    SPUtil.put(SPConfig.TIME,System.currentTimeMillis().toString())
                                }
                                Toast.makeText(context,data.getString("msg"),Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(context,"失败", Toast.LENGTH_SHORT).show()
                        }

                    }

                })
    }
}