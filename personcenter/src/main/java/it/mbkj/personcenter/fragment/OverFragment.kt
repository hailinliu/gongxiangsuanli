package it.mbkj.personcenter.fragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseFragment
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.Yijieshu
import it.mbkj.personcenter.bean.Yunxinghzong
import it.mbkj.personcenter.databinding.FragmentPersoncenterOverBinding
import it.mbkj.personcenter.presenter.OverFragmentPresenter
import net.idik.lib.slimadapter.SlimAdapter

@Route(path = ArouterAddress.OVERFRAGMENT)
class OverFragment:BaseFragment<OverFragmentPresenter>() {
    var adapter: SlimAdapter?=null
    var binding:FragmentPersoncenterOverBinding?=null
    override val layoutId: Int
        get() = R.layout.fragment_personcenter_over
    override val presenter: OverFragmentPresenter
        get() = OverFragmentPresenter()

    override fun isEmpty(textView: TextView?): Boolean {
        return false
    }

    override fun initView() {
      binding = getViewDataBinding<FragmentPersoncenterOverBinding>()
        binding!!.rv.layoutManager = LinearLayoutManager(activity)
        adapter = SlimAdapter.create().register<Yijieshu>(R.layout.fragment_person_item){
                data, injector ->
            injector.visibility(R.id.tv_yiyunxing,View.INVISIBLE)
                .text(R.id.tv_price,data.jiazhi)
                .text(R.id.tv_zong,data.zongshouyi)
                .text(R.id.tv_meiri,data.meirishouyi.toString())
                .text(R.id.tv_period,data.zhouqi.toString())
                .text(R.id.tv_kaishi,data.kaishishijian)
                .visibility(R.id.tv_gou, View.GONE)
        }.attachTo(binding!!.rv)
    }
    override fun initData() {
        mPresenter!!.getList()
    }

    fun setUI(yijiehsu: List<Yijieshu>) {
        adapter!!.updateData(yijiehsu)
    }
}

