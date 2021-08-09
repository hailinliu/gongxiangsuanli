package it.mbkj.personcenter.presenter

import com.alibaba.android.arouter.launcher.ARouter
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import it.mbkj.lib.base.*
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.personcenter.bean.NewPersonBean
import it.mbkj.personcenter.bean.PledgeListBean
import it.mbkj.personcenter.fragment.PersonCenterFragment

class PersonCenterFragmentPresenter: BasePresenter<PersonCenterFragment>() {
    fun getHomePage(session_id:String){
        OkGo.post<BaseBean<NewPersonBean>>(HttpConfig.BASE_URL+"/read/me/detail")
            .params("token",session_id)
            .execute(object : CallBackOption<BaseBean<NewPersonBean>>() {}.loadBind(mView as PersonCenterFragment).execute(
                object :ILoadBind<BaseBean<NewPersonBean>>{
                    override fun excute(bean: BaseBean<NewPersonBean>) {
                         if(bean.code==2){
                             ARouter.getInstance().build(ArouterAddress.LOGINACTIVITY).navigation()
                         }else if(bean.code==1){
                            (mView as PersonCenterFragment).setUI(bean.data)
                        }
                    }
                }
            ))
    }

}