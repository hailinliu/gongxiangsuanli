package it.mbkj.personcenter.presenter

import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.lzy.okgo.OkGo
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseBean
import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.base.ILoadBind
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.bean.PingTaiBean
import it.mbkj.personcenter.fragment.JINBiFragment
import it.mbkj.personcenter.fragment.PingTaiBiFragment

class PingTaiBiFragmentPresenter:BasePresenter<PingTaiBiFragment>() {
    fun getBi(){
        OkGo.post<BaseBean<PingTaiBean>>(HttpConfig.BASE_URL+"/read/me/log")
            .params("token", SPUtil.get(SPConfig.SESSION_ID,""))
            .execute(object : CallBackOption<BaseBean<PingTaiBean>>() {}.loadBind(mView as PingTaiBiFragment).execute(
                object : ILoadBind<BaseBean<PingTaiBean>> {
                    override fun excute(bean: BaseBean<PingTaiBean>) {
                        if(bean.code==2){
                            ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                        }else if (bean.code==1){
                            (mView as PingTaiBiFragment).setUI(bean.data.pingtaibi)
                        }else{
                            Toast.makeText(context,bean.msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ))
    }
}