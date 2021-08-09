package it.mbkj.personcenter.fragment


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kc.openset.OSETBanner
import com.kc.openset.OSETInsert
import com.kc.openset.OSETIntegralWall
import com.kc.openset.OSETListener
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseFragment
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.*
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.NewPersonBean
import it.mbkj.personcenter.databinding.FragmentPersoncenterBinding
import it.mbkj.personcenter.presenter.PersonCenterFragmentPresenter


@Route(path = ArouterAddress.PERSONCENTERFRAGMENT)
class PersonCenterFragment:BaseFragment<PersonCenterFragmentPresenter>(){
    var binding: FragmentPersoncenterBinding?=null
    override val layoutId: Int
        get() = R.layout.fragment_personcenter
    override val presenter: PersonCenterFragmentPresenter
        get() = PersonCenterFragmentPresenter()

    override fun isEmpty(textView: TextView?): Boolean {
        return false
    }

    override fun initView() {
        //super.initView()
        binding= getViewDataBinding<FragmentPersoncenterBinding>()
        RxBus.getDefault().register(this)
       if(TextUtils.isEmpty(SPUtil.get(SPConfig.NUM,""))){
           SPUtil.put(SPConfig.NUM,"2")
       }

        binding!!.imageview.setOnClickListener {
            ARouter.getInstance().build(ArouterAddress.SETTINGACTIVITY).navigation()
        }
        binding!!.ivQiandao.setOnClickListener {
            ARouter.getInstance().build(ArouterAddress.QIANDAOACTIVITY).navigation()
        }
        binding!!.llItem1.setOnClickListener {
            if(Utils.isFastClick()){
                ARouter.getInstance().build(ArouterAddress.ASSETDETAILACTIVITY).navigation()
            }

        }
        binding!!.llItem2.setOnClickListener{
            if(Utils.isFastClick()){
                ARouter.getInstance().build(ArouterAddress.MYJIJIAACTIVITY).navigation()
            }

        }
        binding!!.llItem3.setOnClickListener {
            if(Utils.isFastClick()){

                //AppUserId:媒体的用户id
                //IbxAppKey、IbxAppSecret、YPAppKey找运营要
                OSETIntegralWall.getInstance().setAppUserId(userId).setIbxAppKey("142793309")
                    .setIbxAppSecret("90ae150597b67077").setYPAppKey("542792912")
                OSETIntegralWall.getInstance().showActivity(activity,"B625128C6C2EC54BBA16D5B940C77119", "877D7B398D60E7B676070B3CAAD65B31", "AC8D6B77959E452B1BCAE193E98631FC")
              //  ARouter.getInstance().build(ArouterAddress.QUANMINZHUANACTIVITY).navigation()
            }

        }
        binding!!.llItem4.setOnClickListener {
            if(Utils.isFastClick()){
                ARouter.getInstance().build(ArouterAddress.MYTEAMACTIVITY).navigation()
            }

        }
        binding!!.llItem5.setOnClickListener {
            if(Utils.isFastClick()){
                ARouter.getInstance().build(ArouterAddress.INVITEFRIENDSACTIVITY).navigation()
            }

        }
        binding!!.llItem6.setOnClickListener {
            if(Utils.isFastClick()){
                if(is_real==1){
                    Toast.makeText(activity, "已认证", Toast.LENGTH_SHORT).show()
                   // ToastUtil.showShort("已认证")
                }else {
                   var time= SPUtil.get(SPConfig.TIME,"")
                    if(!TextUtils.isEmpty(time)){
                       var data= System.currentTimeMillis()-time!!.toLong()
                        if(data>24*3600*1000){
                            SPUtil.put(SPConfig.NUM,"2")
                            ARouter.getInstance().build(ArouterAddress.SHIMINGACTIVITY).navigation()
                        }else{
                           var num= SPUtil.get(SPConfig.NUM,"")
                            if(num!!.toInt()>0){
                                ARouter.getInstance().build(ArouterAddress.SHIMINGACTIVITY).navigation()
                            }else{
                                ToastUtil.showShort("你认证次数已用尽，请明天再来!")
                            }
                        }
                    }else{
                        ARouter.getInstance().build(ArouterAddress.SHIMINGACTIVITY).navigation()
                    }

                }

            }

        }
        binding!!.llItem7.setOnClickListener {
            if(Utils.isFastClick()){
                ARouter.getInstance().build(ArouterAddress.ABOUTUSACTIVITY).navigation()
            }

        }
        binding!!.llItem8.setOnClickListener {
            if(Utils.isFastClick()){
                Toast.makeText(activity, "暂未开放", Toast.LENGTH_SHORT).show()
              //  ARouter.getInstance().build(ArouterAddress.CONACTUSACTIVITY).navigation()
            }

        }
        binding!!.image.setOnClickListener {
            if(Utils.isFastClick()){
               // ARouter.getInstance().build(ArouterAddress.ZICHANXINXIACTIVITY).navigation()
            }

        }
        OSETInsert.getInstance().show(activity, "AC8D6B77959E452B1BCAE193E98631FC", object :
            OSETListener {
            override fun onClick() {
             /*   Toast.makeText(
                    activity,
                    "onClick",
                    Toast.LENGTH_SHORT
                ).show()*/
            }

            override fun onError(s: String?, s1: String?) {
               /* Toast.makeText(
                    activity,
                    "onError",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("openseterror", "code:$s----message:$s1")*/
            }

            override fun onItemError(p0: String?, p1: String?) {

            }

            override fun onClose() {
              /*  Toast.makeText(
                    activity,
                    "onClose",
                    Toast.LENGTH_SHORT
                ).show()*/
            }

            override fun onShow() {
              /*  Toast.makeText(activity, "onShow", Toast.LENGTH_SHORT)
                    .show()*/
            }

        })
        OSETBanner.getInstance().setWHScale(0.15625) //只对穿山甲起作用

        OSETBanner.getInstance()
            .show(activity, "B625128C6C2EC54BBA16D5B940C77119", binding!!.fl, object : OSETListener {
                override fun onShow() {
                    // Toast.makeText(activity, "onShow", Toast.LENGTH_SHORT).show()
                }

                override fun onError(s: String, s1: String) {
                    /* Toast.makeText(activity, "onError", Toast.LENGTH_SHORT).show()
                     Log.e("openseterror", "code:$s----message:$s1")*/
                }

                override fun onItemError(s: String, s1: String) {
                    //用于开发直接看每一个上游的错误信息。
                }

                override fun onClick() {
                    // Toast.makeText(activity, "onClick", Toast.LENGTH_SHORT).show()
                }

                override fun onClose() {
                    //Toast.makeText(activity, "onClose", Toast.LENGTH_SHORT).show()
                }
            })
    }
    @Subscribe(threadMode = ThreadMode.MAIN,code=1357)
    fun refresh(){
        SPUtil.get(SPConfig.SESSION_ID, "")?.let { mPresenter!!.getHomePage(it) }
    }
   /* @Subscribe(threadMode = ThreadMode.MAIN,code=1010)
    fun refresh1(){
        SPUtil.get(SPConfig.SESSION_ID, "")?.let { mPresenter!!.getHomePage(it) }
    }*/
   public override fun initData() {
       SPUtil.get(SPConfig.SESSION_ID, "")?.let { mPresenter!!.getHomePage(it) }
    }
private var is_real:Int?=null
    private var userId:String?=null
    fun setUI(userMsg: NewPersonBean) {
        binding!!.tvName.text = "昵称:"+userMsg.nickname
        binding!!.tvPhone.text = userMsg.phone
        binding!!.tvJin.text = userMsg.jinbi
        binding!!.tvS.text = userMsg.pingtaibi
        binding!!.tvDengji.text = userMsg.level
        is_real = userMsg.is_real
        userId = userMsg.userId
        /*if(userMsg.is_real==1){
            binding!!.llItem6.isClickable = false
            binding!!.llItem6.isEnabled =false
        }else{
            binding!!.llItem6.isClickable = true
            binding!!.llItem6.isEnabled =true
        }*/
       // ImageUtil.setCircleImage(HttpConfig.BASE_IMG_URL+userMsg.head_img,binding!!.image)
        //tv_team.text =
    }


}