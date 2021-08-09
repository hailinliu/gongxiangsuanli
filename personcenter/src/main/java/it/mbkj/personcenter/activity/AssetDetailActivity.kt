package it.mbkj.personcenter.activity

import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.bean.TabItem
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.PingTaiBean
import it.mbkj.personcenter.fragment.JINBiFragment
import it.mbkj.personcenter.fragment.PersonCenterFragment
import it.mbkj.personcenter.fragment.PingTaiBiFragment
import it.mbkj.personcenter.presenter.AssetDetailActivityPresenter
import kotlinx.android.synthetic.main.activity_asset_detail.*

@Route(path = ArouterAddress.ASSETDETAILACTIVITY)
class AssetDetailActivity:BaseActivity<AssetDetailActivityPresenter>() {
    private var fragments: ArrayList<Fragment>? = null
    var tabMap = SparseArray<Fragment>()
    private var curPos = 0
    override val layoutId: Int
        get() = R.layout.activity_asset_detail
    override val presenter: AssetDetailActivityPresenter
        get() = AssetDetailActivityPresenter()
    override fun initView() {
        setTitle("资产明细")
        val tabItems = ArrayList<CustomTabEntity>()
        tabItems.add(TabItem("金币"))
        tabItems.add(TabItem("SCPC"))
        fragments = ArrayList()
        fragments?.add(
            (ARouter.getInstance().build(ArouterAddress.JINBIFRAGMENT).navigation() as JINBiFragment)
        )
        fragments?.add(
            (ARouter.getInstance().build(ArouterAddress.PINGTAIBIFRAGMENT).navigation() as PingTaiBiFragment)
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