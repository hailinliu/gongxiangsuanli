package it.mbkj.personcenter.activity

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.*
import it.mbkj.personcenter.databinding.ActivityMyTeamBinding
import it.mbkj.personcenter.presenter.MyTeamActivityPresenter
import net.idik.lib.slimadapter.SlimAdapter
import kotlin.collections.ArrayList

@Route(path = ArouterAddress.MYTEAMACTIVITY)
class MyTeamActivity:BaseActivity<MyTeamActivityPresenter>() {
    var binding:ActivityMyTeamBinding?=null
    var adapter: SlimAdapter?=null
    override val layoutId: Int
        get() = R.layout.activity_my_team
    override val presenter: MyTeamActivityPresenter
        get() = MyTeamActivityPresenter()

    override fun initView() {
       binding=  getViewDataBinding<ActivityMyTeamBinding>()
        setTitle("我的社区")

        binding!!.rv.layoutManager = LinearLayoutManager(this)
        adapter = SlimAdapter.create().register<User>(R.layout.activity_my_team_item){ data, injector ->
            injector.text(R.id.tv_name, data.phone)
                .text(R.id.tv_num, data.register_time)
        }
            .attachTo(binding!!.rv)

    }

    override fun initData() {
        SPUtil.get(SPConfig.SESSION_ID, "")?.let { mPresenter!!.getTeam(it) }
       // binding!!.tvZhitui.text =
    }

    override fun initEvent() {
       // super.initEvent()
        binding!!.commonTabLayout.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                if(position==0){
                    adapter!!.updateData(list1)
                }else if(position==1){
                    adapter!!.updateData(list2)
                }
            }

            override fun onTabReselect(position: Int) {
            }
        })
    }
   var list1:List<User> = ArrayList()
    var list2:List<User> = ArrayList()
    fun setUI(data: MyTeamBean) {
       list1= data.users
       list2= data.valid_users
        val tabItems = ArrayList<CustomTabEntity>()
        tabItems.add(TabItem("直推人数"+data.users_count.toString()))
        tabItems.add(TabItem("有效人数"+data.valid_users_count.toString()))
        binding!!.commonTabLayout.setTabData(tabItems)
        adapter!!.updateData(list1)
    }
}