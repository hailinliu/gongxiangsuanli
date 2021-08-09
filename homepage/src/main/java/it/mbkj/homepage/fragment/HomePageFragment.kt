package it.mbkj.homepage.fragment

import android.Manifest
import android.Manifest.permission.READ_PHONE_STATE
import android.content.pm.PackageManager
import android.os.Build
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.alibaba.android.arouter.facade.annotation.Route
import com.kc.openset.OSETNews
import it.mbkj.homepage.R
import it.mbkj.homepage.presenter.HomePageFragmentPresenter
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseFragment

@Route(path = ArouterAddress.HOMEPAGEFRAGMENT)
class HomePageFragment :BaseFragment<HomePageFragmentPresenter>(){
/*val params = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)*/
    override val layoutId: Int
        get() = R.layout.homepage_new
    override val presenter: HomePageFragmentPresenter
        get() = HomePageFragmentPresenter()

    override fun isEmpty(textView: TextView?): Boolean {
        return false
    }

    fun myPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ), PackageManager.PERMISSION_GRANTED
                )
            }
        }
    }

    override fun initView() {
        myPermission()
        OSETNews.getInstance().setVerfiy(true)
        OSETNews.getInstance().setInsertId("AC8D6B77959E452B1BCAE193E98631FC")
        OSETNews.getInstance().setBannerId("B625128C6C2EC54BBA16D5B940C77119")
        childFragmentManager.beginTransaction().replace(
            R.id.fl, OSETNews.getInstance().getNewsFragment(
                activity,
                "547F62AC022D3A78509DA2A476BA1210",
                "",
                5
            )
        ).commit()


    }


    override fun initEvent() {



        }
    }



