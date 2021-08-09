package it.mbkj.personcenter.activity

import android.graphics.Bitmap
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.http.HttpConfig
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.YaoQingBean
import it.mbkj.personcenter.databinding.ActivityInviteBinding
import it.mbkj.personcenter.presenter.InviteFriendsActivityPresenter
import kotlinx.android.synthetic.main.activity_invite.*

@Route(path = ArouterAddress.INVITEFRIENDSACTIVITY)
class InviteFriendsActivity: BaseActivity<InviteFriendsActivityPresenter>() {
    var binding:ActivityInviteBinding?=null

    override val layoutId: Int
        get() = R.layout.activity_invite
    override val presenter: InviteFriendsActivityPresenter
        get() = InviteFriendsActivityPresenter()

    override fun initView() {
       binding = getViewDataBinding<ActivityInviteBinding>()
        setTitle("邀请好友")
        binding!!.include.tvTitle.setTextColor(resources.getColor(R.color.lib_view))
        binding!!.include.leftImg.setColorFilter(resources.getColor(R.color.lib_view))
    }

    override fun initData() {
        SPUtil.get(SPConfig.SESSION_ID, "")?.let { mPresenter!!.getInvite(it) }
    }
    fun getData(data: YaoQingBean) {
        binding!!.tvYaoqingmaContent.text = data.invite_code

    Glide.with(this).applyDefaultRequestOptions(RequestOptions()).load(HttpConfig.BASE_URL+data.invite_qrcode).into(binding!!.image1)


    }


}