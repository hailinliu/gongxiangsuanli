package it.mbkj.personcenter.activity

import android.util.SparseArray
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.bean.TabItem
import it.mbkj.lib.http.SPConfig
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.DataT
import it.mbkj.personcenter.databinding.ActivityMyJijiaBinding
import it.mbkj.personcenter.fragment.JINBiFragment
import it.mbkj.personcenter.fragment.OverFragment
import it.mbkj.personcenter.fragment.PingTaiBiFragment
import it.mbkj.personcenter.fragment.RunFragment
import it.mbkj.personcenter.presenter.MyJiJiaActivityPresenter
import kotlinx.android.synthetic.main.activity_asset_detail.*
import net.idik.lib.slimadapter.SlimAdapter

@Route(path = ArouterAddress.MYJIJIAACTIVITY)
class MyJiJiaActivity:BaseActivity<MyJiJiaActivityPresenter>() {
    var binding:ActivityMyJijiaBinding?=null
    private var fragments: ArrayList<Fragment>? = null
    var tabMap = SparseArray<Fragment>()
    private var curPos = 0
    override val layoutId: Int
        get() = R.layout.activity_my_jijia
    override val presenter: MyJiJiaActivityPresenter
        get() = MyJiJiaActivityPresenter()

    override fun initView() {
       binding=  getViewDataBinding<ActivityMyJijiaBinding>()
        setTitle("算力订单")
        val tabItems = ArrayList<CustomTabEntity>()
        tabItems.add(TabItem("运行中"))
        tabItems.add(TabItem("已结束"))
        fragments = ArrayList()
        fragments?.add(
            (ARouter.getInstance().build(ArouterAddress.RUNFRAGMENT).navigation() as RunFragment)
        )
        fragments?.add(
            (ARouter.getInstance().build(ArouterAddress.OVERFRAGMENT).navigation() as OverFragment)
        )
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, fragments!![0])
            .show(fragments!![0])
            .commitAllowingStateLoss()
        tabMap.put(0, fragments!![0])
        commonTabLayout.setTabData(tabItems)
        commonTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                changeTab(position)
            }

            override fun onTabReselect(position: Int) {}
        })
    }
    private fun changeTab(position: Int) {
        if (tabMap[position] == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout, fragments!![position])
                //.add()
                .hide(fragments!![curPos])
                .show(fragments!![position])
                .commitAllowingStateLoss()
            tabMap.put(position, fragments!![position])
        } else {
            supportFragmentManager
                .beginTransaction()
                .hide(fragments!![curPos])
                .show(fragments!![position])
                .commitAllowingStateLoss()


        }
        curPos = position

    }
    }


