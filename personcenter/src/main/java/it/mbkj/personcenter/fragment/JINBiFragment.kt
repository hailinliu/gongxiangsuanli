package it.mbkj.personcenter.fragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseFragment
import it.mbkj.lib.http.SPConfig
import it.mbkj.personcenter.R
import it.mbkj.personcenter.bean.JinBi
import it.mbkj.personcenter.databinding.ActivityJineBinding
import it.mbkj.personcenter.presenter.JINBiFragmentPresenter
import net.idik.lib.slimadapter.SlimAdapter

@Route(path = ArouterAddress.JINBIFRAGMENT)
class JINBiFragment:BaseFragment<JINBiFragmentPresenter>() {
    var adapter: SlimAdapter?=null
    var binding:ActivityJineBinding?=null
    override val layoutId: Int
        get() = R.layout.activity_jine
    override val presenter: JINBiFragmentPresenter
        get() = JINBiFragmentPresenter()

    override fun isEmpty(textView: TextView?): Boolean {
        return false
    }

    override fun initView() {
        //super.initView()
       binding = getViewDataBinding<ActivityJineBinding>()
       binding!!.rv.layoutManager = LinearLayoutManager(activity)
        adapter = SlimAdapter.create().register<JinBi>(R.layout.activity_jine_item){
                data, injector ->
            injector.text(R.id.tv_num,data.number)
                .text(R.id.tv_type,data.type)
                .text(R.id.tv_time,data.time)
        }.attachTo(binding!!.rv)
    }

    override fun initData() {
        mPresenter!!.getBi()
    }
    fun setUI(jinbi: List<JinBi>) {
        adapter!!.updateData(jinbi)
    }
}