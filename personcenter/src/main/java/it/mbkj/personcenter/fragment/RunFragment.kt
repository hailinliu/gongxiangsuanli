package it.mbkj.personcenter.fragment

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.openset.OSETRewardVideo
import com.kc.openset.OSETVideoListener
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseFragment
import it.mbkj.lib.base.LogUtil
import it.mbkj.lib.utils.RxBus
import it.mbkj.lib.utils.Utils
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.Log
import it.mbkj.personcenter.bean.Yunxinghzong
import it.mbkj.personcenter.databinding.FragmentPersoncenterBinding
import it.mbkj.personcenter.databinding.FragmentPersoncenterRunBinding
import it.mbkj.personcenter.presenter.RunFragmentPresenter
import net.idik.lib.slimadapter.SlimAdapter

@Route(path = ArouterAddress.RUNFRAGMENT)
class RunFragment:BaseFragment<RunFragmentPresenter>() {

    override val layoutId: Int
        get() = R.layout.fragment_personcenter_run
    override val presenter: RunFragmentPresenter
        get() = RunFragmentPresenter()

    override fun isEmpty(textView: TextView?): Boolean {
        return false
    }
    var adapter: SlimAdapter?=null
    var binding: FragmentPersoncenterRunBinding?=null


    override fun initView() {
        //super.initView()
        binding = getViewDataBinding<FragmentPersoncenterRunBinding>()
        RxBus.getDefault().register(this)
        binding!!.rv.layoutManager = LinearLayoutManager(activity)
        adapter = SlimAdapter.create().register<Yunxinghzong>(R.layout.fragment_person_item){
                data, injector ->
            injector.text(R.id.tv_yiyunxing,"已运行"+data.yiyunxingtianshu+"天")
                    .text(R.id.tv_name,data.name)
                    .text(R.id.tv_price,data.jiazhi)
                    .text(R.id.tv_zong,data.zongshouyi)
                    .text(R.id.tv_meiri,data.meirishouyi.toString())
                    .text(R.id.tv_period,data.zhouqi.toString())
                    .text(R.id.tv_kaishi,data.kaishishijian)
                    .text(R.id.tv_gou,if(data.shengyukanguanggaocishu==0) "已领取" else "看广告领取收益("+data.shengyukanguanggaocishu.toString()+")")
                    .background(R.id.tv_gou,if(data.shengyukanguanggaocishu==0) resources.getDrawable(R.drawable.lib_button2) else resources.getDrawable(R.drawable.lib_button))
            var tv_gou =   injector.findViewById<TextView>(R.id.tv_gou)
            tv_gou.setOnClickListener {
                if(Utils.isFastClick()){
                    OSETRewardVideo.getInstance().setVerify(true);//设置需要验证key（后台验证）
                    OSETRewardVideo.getInstance().load(activity, "5B4A7CA6128265F1FE71695244233887", object :
                        OSETVideoListener {
                        override fun onShow() {

                        }

                        override fun onError(p0: String?, p1: String?) {

                        }

                        override fun onItemError(p0: String?, p1: String?) {

                        }

                        override fun onClick() {

                        }

                        override fun onClose(p0: String?) {
                            mPresenter!!.getList()
                            RxBus.getDefault().send(1357)
                        }

                        override fun onVideoEnd(p0: String?) {

                        }

                        override fun onLoad() {
                            OSETRewardVideo.getInstance().showRewardAd(activity)
                        }

                        override fun onVideoStart() {

                        }

                        override fun onReward(p0: String?) {
                            mPresenter!!.getBuy(data.id.toString())


                        }

                    })
                }

            }
            if(data.shengyukanguanggaocishu!=0){
               tv_gou.isClickable= true
               tv_gou.isEnabled = true
            }else{
                tv_gou.isClickable= false
                tv_gou.isEnabled = false
            }
        }.attachTo(binding!!.rv)
    }

    override fun initData() {
        mPresenter!!.getList()
    }

    fun setUI(yunxinghzong: List<Yunxinghzong>) {
        adapter!!.updateData(yunxinghzong)
    }

    override fun onDestroy() {
        super.onDestroy()
        OSETRewardVideo.getInstance().destory()
    }
    /*  fun setUI(jinbi: List<JinBi>) {
          adapter!!.updateData(jinbi)
      }*/
}