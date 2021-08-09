package it.mbkj.personcenter.presenter

import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.lzy.okgo.OkGo

import it.mbkj.lib.base.*
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.bean.RunBean
import it.mbkj.personcenter.fragment.OverFragment
import it.mbkj.personcenter.fragment.RunFragment

class OverFragmentPresenter:BasePresenter<OverFragment>() {
    fun getList(){
        OkGo.post<BaseBean<RunBean>>(HttpConfig.BASE_URL+"/read/me/myreadmill")
            .params("token", SPUtil.get(SPConfig.SESSION_ID,""))
            .execute(object : CallBackOption<BaseBean<RunBean>>(){

            }.loadBind(mView as OverFragment).execute( object : ILoadBind<BaseBean<RunBean>> {
                override fun excute(bean: BaseBean<RunBean>) {
                    if(bean.code==2){
                        ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                    }else if (bean.code==1){
                        (mView as OverFragment).setUI(bean.data.yijieshu)
                    }else{
                       Toast.makeText(context,bean.msg,Toast.LENGTH_SHORT).show()
                    }
                }
            }))
    }
}