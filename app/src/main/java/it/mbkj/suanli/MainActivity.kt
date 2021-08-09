package it.mbkj.suanli


import android.util.SparseArray
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.flyco.tablayout.listener.CustomTabEntity
import it.mbkj.homepage.fragment.HomePageFragment
import it.mbkj.lib.base.App
import it.mbkj.suanli.presenter.MainActivityPresenter
import it.mbkj.lib.base.AppManager
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.bean.TabItem
import it.mbkj.lib.utils.RxBus
import it.mbkj.lib.utils.Subscribe
import it.mbkj.lib.utils.ThreadMode
import it.mbkj.personcenter.fragment.PersonCenterFragment
import it.mbkj.tradecenter.fragment.TradCenterFragment
import it.mbkj.tradecenter.fragment.XingQiuFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


@Route(path = ArouterAddress.MAINACTIVITY)
class MainActivity : BaseActivity<MainActivityPresenter>() {
    @JvmField
    @Autowired
    var sessionId:String?=null
    private var fragments: ArrayList<Fragment>? = null
    var tabMap = SparseArray<Fragment>()
    private var curPos = 0
    override fun initView() {
        ARouter.getInstance().inject(this)
        RxBus.getDefault().register(this)
        val tabItems = ArrayList<CustomTabEntity>()
        tabItems.add(
            TabItem(
                "首页",
                R.mipmap.lib_icon_menu_first_unselect,
                R.mipmap.lib_icon_menu_first_select
            )
        )
        tabItems.add(
            TabItem(
                "积分池",
                R.mipmap.lib_icon_menu_second_unselect,
                R.mipmap.lib_icon_menu_second_select
            )
        )
        tabItems.add(
            TabItem(
                "短视频",
                R.mipmap.app_third_unselected,
                R.mipmap.app_third_select
            )
        )
        tabItems.add(
            TabItem(
                "我的",
                R.mipmap.lib_icon_menu_four_unselect,
                R.mipmap.lib_icon_menu_four_select
            )
        )
        fragments = ArrayList()
        fragments?.add(
            (ARouter.getInstance().build(ArouterAddress.HOMEPAGEFRAGMENT).withString("sessionId",sessionId).withInt("flag",1).navigation() as HomePageFragment)
        )
        fragments?.add(
            (ARouter.getInstance().build(ArouterAddress.XINGQIUFRAGMENT).navigation() as XingQiuFragment)
        )
        fragments?.add(
            (ARouter.getInstance().build(ArouterAddress.TRADCENTERFRAGMENT).navigation() as TradCenterFragment)
        )
        fragments?.add(
            (ARouter.getInstance().build(ArouterAddress.PERSONCENTERFRAGMENT).navigation() as PersonCenterFragment)
        )
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, fragments!![0])
            .show(fragments!![0])
            .commitAllowingStateLoss()
        tabMap.put(0, fragments!![0])
        commonTabLayout.setTabData(tabItems)
        commonTabLayout.setOnTabSelectListener(object :
            com.flyco.tablayout.listener.OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                changeTab(position)
            }

            override fun onTabReselect(position: Int) {}
        })
    }
    @Subscribe(threadMode = ThreadMode.MAIN,code=1010)
    fun getCode(){
        changeTab(3)
        commonTabLayout.currentTab =3
        for (i in supportFragmentManager.fragments){
            if(i is PersonCenterFragment){
                (i as PersonCenterFragment).initData()
            }
        }
        }
    override val layoutId: Int
        get() = R.layout.activity_main
    override val presenter: MainActivityPresenter
        get() = MainActivityPresenter()
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
    private var oldTime: Long = 0
    override fun onBackPressed() {
        val nowTime = Date().time
        if (nowTime - oldTime <= 2000) {
            AppManager.getSingleton().AppExit(this)
        } else {
            oldTime = nowTime
            //ToastUtil.showShort(App.INSTANCE.getString(R.string.libbaseActivity1))
            Toast.makeText(this, App.INSTANCE!!.getString(R.string.libbaseActivity1),Toast.LENGTH_SHORT).show()
        }
    }


}
