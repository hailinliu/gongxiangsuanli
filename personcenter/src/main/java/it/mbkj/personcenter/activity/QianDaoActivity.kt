package it.mbkj.personcenter.activity

import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.openset.OSETRewardVideo
import com.kc.openset.OSETVideoListener
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.utils.Utils
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.Log
import it.mbkj.personcenter.bean.QianDaoBean
import it.mbkj.personcenter.databinding.ActivityQiandaoBinding
import it.mbkj.personcenter.presenter.QianDaoActivityPresenter
import net.idik.lib.slimadapter.SlimAdapter

@Route(path = ArouterAddress.QIANDAOACTIVITY)
class QianDaoActivity:BaseActivity<QianDaoActivityPresenter>() {
    var adapter: SlimAdapter?=null
    var binding:ActivityQiandaoBinding?=null
    override val layoutId: Int
        get() = R.layout.activity_qiandao
    override val presenter: QianDaoActivityPresenter
        get() = QianDaoActivityPresenter()

    override fun initView() {
        setTitle("签到")
       binding= getViewDataBinding<ActivityQiandaoBinding>()
        binding!!.rv.layoutManager = LinearLayoutManager(this)
        adapter = SlimAdapter.create().register<Log>(R.layout.activity_qiandao_item){ data, injector ->
            injector
                .text(R.id.tv_time, data.time)
                .text(R.id.tv_content, "获取" + data.number + "SCPC")

        }.attachTo(binding!!.rv)
    }
    fun setUI(data: QianDaoBean) {
    adapter!!.updateData(data.log)
        binding!!.tvZongshouyi.text = data.count.toString()
        binding!!.tvYushouyi.text = data.get+"SCPC"
    }
var flag:Boolean = false
    override fun initData() {
       mPresenter!!.getList()
    }
    override fun initEvent() {
        binding!!.smart.setOnRefreshListener {
            mPresenter!!.getList()
            binding!!.smart.finishRefresh()
        }
        binding!!.tvQiandao.setOnClickListener {
            if(Utils.isFastClick()){
                OSETRewardVideo.getInstance().setVerify(true);//设置需要验证key（后台验证）
                OSETRewardVideo.getInstance().load(
                    this,
                    "4D25CFE66049F165484BC62C64A855F3",
                    object :
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
                            if (flag) {
                                mPresenter!!.qiandao()
                            }
                        }

                        override fun onVideoEnd(p0: String?) {

                        }

                        override fun onLoad() {
                            OSETRewardVideo.getInstance().showRewardAd(this@QianDaoActivity)
                        }

                        override fun onVideoStart() {

                        }

                        override fun onReward(p0: String?) {
                            flag = true

                        }

                    })
            }

           // ARouter.getInstance().build(ArouterAddress.JILIGUANGGAOACTIVITY).navigation()
        }
    }

    fun setdata(msg: String) {
        mPresenter!!.getList()
        OSETRewardVideo.getInstance().destory()
    }


}