package it.mbkj.tradecenter.fragment

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.openset.OSETBanner
import com.kc.openset.OSETInsert
import com.kc.openset.OSETListener
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseFragment
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.SPUtil
import it.mbkj.tradecenter.presenter.XingQiuFragmentPresenter
import it.mbkj.xingqiu.R
import it.mbkj.xingqiu.bean.Person
import it.mbkj.xingqiu.databinding.FragmentXingqiuBinding
import net.idik.lib.slimadapter.SlimAdapter
import java.math.BigDecimal


@Route(path = ArouterAddress.XINGQIUFRAGMENT)
class XingQiuFragment:BaseFragment<XingQiuFragmentPresenter>() {
    var adapter: SlimAdapter?=null
    var binding:FragmentXingqiuBinding?=null
    override val layoutId: Int
        get() = R.layout.fragment_xingqiu
    override val presenter: XingQiuFragmentPresenter
        get() = XingQiuFragmentPresenter()

    override fun isEmpty(textView: TextView?): Boolean {
        return false
    }

    override fun initView() {
        binding  = getViewDataBinding<FragmentXingqiuBinding>()
        binding!!.rv.layoutManager = LinearLayoutManager(activity)
        adapter = SlimAdapter.create().register<Person>(R.layout.fragment_xingqiu_item){ data, injector ->
                injector.text(R.id.tv_price, data.danjia.toString())
                    .text(R.id.tv_name,data.name)
                    .text(R.id.tv_zong, data.zongshouyi.toString())
                    .text(R.id.tv_meiri, BigDecimal(data.meirishouyi).setScale(4,BigDecimal.ROUND_DOWN).toPlainString() + "scpc")
                    .text(R.id.tv_period, data.zhouqi.toString() + "天")
                    .clicked(R.id.tv_gou, View.OnClickListener {
                        var alertDialog = AlertDialog.Builder(context)
                           var contentview= View.inflate(context,R.layout.fragment_alert_dialog,null)
                            alertDialog.setView(contentview)
                          var dialog=  alertDialog.create()
                            dialog.show()
                          var tvSure =  contentview.findViewById<TextView>(R.id.tv_sure)
                         var tvCancel =   contentview.findViewById<TextView>(R.id.tv_cancel)
                            tvSure.setOnClickListener {SPUtil.get(SPConfig.SESSION_ID, "")?.let { it1 ->
                                mPresenter!!.buy(
                                    it1,
                                    data.readmill_id
                                ) }
                                dialog.dismiss()
                        }
                        tvCancel.setOnClickListener {
                            dialog.dismiss()
                        }
                    }
                        )
        }.attachTo(binding!!.rv)
        OSETInsert.getInstance().show(activity, "AC8D6B77959E452B1BCAE193E98631FC", object :
            OSETListener {
            override fun onClick() {
                /* Toast.makeText(
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
                /*Toast.makeText(
                    activity,
                    "onClose",
                    Toast.LENGTH_SHORT
                ).show()*/
            }

            override fun onShow() {
                /*   Toast.makeText(activity, "onShow", Toast.LENGTH_SHORT)
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

  public override fun initData() {
      SPUtil.get(SPConfig.SESSION_ID, "")?.let { mPresenter!!.getList(it) }
    }

    override fun initEvent() {
        //super.initEvent()
        binding!!.smart.setOnRefreshListener {
            SPUtil.get(SPConfig.SESSION_ID, "")?.let { it1 -> mPresenter!!.getList(it1) }
            it.finishRefresh(2000)
        }
    }

    fun setUI(list: List<Person>) {
        /*var newList = ArrayList<Person>()
        newList.add(list.get(0))*/
        adapter!!.updateData(list)
    }
    /*fun setUI(data: List<DataX>) {
        adapter!!.updateData(data)
    }*/


}