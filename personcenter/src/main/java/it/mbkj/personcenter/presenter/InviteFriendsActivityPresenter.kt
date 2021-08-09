package it.mbkj.personcenter.presenter

import com.lzy.okgo.OkGo

import it.mbkj.lib.base.BaseBean
import it.mbkj.lib.base.BasePresenter
import it.mbkj.lib.base.ILoadBind
import it.mbkj.lib.http.CallBackOption
import it.mbkj.lib.http.HttpConfig
import it.mbkj.personcenter.activity.InviteFriendsActivity
import it.mbkj.personcenter.bean.YaoQingBean

class InviteFriendsActivityPresenter:BasePresenter<InviteFriendsActivity>() {
  fun  getInvite(session_id:String){
        OkGo.post<BaseBean<YaoQingBean>>(HttpConfig.BASE_URL+"/read/me/invite")
            .params("token",session_id)
            .execute(object : CallBackOption<BaseBean<YaoQingBean>>() {}.loadBind(mView as InviteFriendsActivity).execute(
                object : ILoadBind<BaseBean<YaoQingBean>> {
                    override fun excute(bean: BaseBean<YaoQingBean>) {
                        if(bean.code==1){
                            (mView as InviteFriendsActivity).getData(bean.data)
                            //ToastUtil.showShort(bean.msg)

                        }
                    }
                }
            ))

    }
}