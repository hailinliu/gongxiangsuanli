package it.mbkj.personcenter.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.alibaba.fastjson.JSONObject
import com.shumai.liveness.LivenessMainActivity
import it.mbkj.lib.base.AppManager
import it.mbkj.lib.base.ArouterAddress
import it.mbkj.lib.base.BaseActivity
import it.mbkj.lib.http.SPConfig
import it.mbkj.lib.utils.MD5Utils
import it.mbkj.lib.utils.RxBus
import it.mbkj.lib.utils.SPUtil
import it.mbkj.personcenter.R
import it.mbkj.personcenter.databinding.ActivityFaceBinding
import it.mbkj.personcenter.presenter.FaceActivityPresenter
@Route(path = ArouterAddress.FACEACTIVITY)
class FaceActivity:BaseActivity<FaceActivityPresenter>() {
    @JvmField
    @Autowired
    var idcard:String?=null
    @JvmField
    @Autowired
    var name:String?=null
    val START_LIVE_DETECT:Int =99
    val TAG = "LIVENESS_TEST"
    override val layoutId: Int
        get() = R.layout.activity_face
    override val presenter: FaceActivityPresenter
        get() = FaceActivityPresenter()

    override fun initView() {
        ARouter.getInstance().inject(this)
      var binding:ActivityFaceBinding =  getViewDataBinding<ActivityFaceBinding>()
        binding!!.include.tvTitle.setTextColor(resources.getColor(R.color.lib_view))
        binding!!.include.leftImg.setColorFilter(resources.getColor(R.color.lib_view))
        binding!!.tvSure.setOnClickListener {
            val intent = Intent(
                this@FaceActivity,
                LivenessMainActivity::class.java
            )
           // CustomApplication.getInstance().setLiveType(LIVE_TYPE_LIVE_DETECT)
            val bundle = Bundle()

            /*********活体参数*************/
            /*********活体参数 */
            bundle.putString("actions", "1279") // 动作组合 1 摇头 2点头 7张嘴 9眨眼

            bundle.putString("actionsNum", "3") // 检测动作数目


            /********* UI 设置**********************
            UIConfig config = new UIConfig();
            config.setBottomAreaBgColor("026a86"); // 屏幕下方颜色 026a86
            config.setNoticeTextColor("FFFFFF"); // 动画上方动作提示文字颜色 FFFFFF
            config.setNoticeTextSize("22"); // 动画上方动作提示文字大小 22

            config.setNavTitleColor("FFFFFF");// 导航栏标题颜色 FFFFFF
            config.setNavBgColor("0186aa"); // 导航栏背景色 0186aa
            config.setNavTitle("活体检测"); // 导航栏标题 活体检测
            config.setNavTitleSize("24"); // 导航栏标题大小 24

            config.setRoundBgColor("004b5e"); // 动画倒计时 进度条背景色 004b5e
            config.setRoundProgressColor("ed7d00"); // 动画倒计时 进度条颜色 ed7d00
            bundle.putSerializable("uiConfig", config);
             *******************************/

            /*********密钥设置*************/
            /**-- 密钥配置, 2种方式
            1. AndroidManifest.xml application 标签中 meta-data 配置, 如下:
            <meta-data
            android:name="liveness_app_secrect"
            android:value="通过数脉申请" />
            <meta-data
            android:name="liveness_app_id"
            android:value="通过数脉申请" />
            2. 通过接口调用, 如下
            JSONObject obj = new JSONObject();
            obj.put("liveness_app_id", "通过数脉申请");
            obj.put("liveness_app_secrect", "通过数脉申请");
            bundle.putString("bizData", obj.toJSONString());
             *******************************************************/

            /********* UI 设置**********************
             * UIConfig config = new UIConfig();
             * config.setBottomAreaBgColor("026a86"); // 屏幕下方颜色 026a86
             * config.setNoticeTextColor("FFFFFF"); // 动画上方动作提示文字颜色 FFFFFF
             * config.setNoticeTextSize("22"); // 动画上方动作提示文字大小 22
             *
             * config.setNavTitleColor("FFFFFF");// 导航栏标题颜色 FFFFFF
             * config.setNavBgColor("0186aa"); // 导航栏背景色 0186aa
             * config.setNavTitle("活体检测"); // 导航栏标题 活体检测
             * config.setNavTitleSize("24"); // 导航栏标题大小 24
             *
             * config.setRoundBgColor("004b5e"); // 动画倒计时 进度条背景色 004b5e
             * config.setRoundProgressColor("ed7d00"); // 动画倒计时 进度条颜色 ed7d00
             * bundle.putSerializable("uiConfig", config);
             */
            /*********密钥设置 */
            /**-- 密钥配置, 2种方式
             * 1. AndroidManifest.xml application 标签中 meta-data 配置, 如下:
             * <meta-data android:name="liveness_app_secrect" android:value="通过数脉申请"></meta-data>
             * <meta-data android:name="liveness_app_id" android:value="通过数脉申请"></meta-data>
             * 2. 通过接口调用, 如下
             * JSONObject obj = new JSONObject();
             * obj.put("liveness_app_id", "通过数脉申请");
             * obj.put("liveness_app_secrect", "通过数脉申请");
             * bundle.putString("bizData", obj.toJSONString());
             */
            val obj = JSONObject()
            obj.put("liveness_app_id", "ZdS6tcUDkoWfMIuI") // 数脉申请

            obj.put("liveness_app_secrect", "ZdS6tcUDkoWfMIuIbGTEvwXHBBe35z6o") // 数脉申请

            bundle.putString("bizData", obj.toJSONString())

            intent.putExtra("liveness", bundle)

            startActivityForResult(intent, START_LIVE_DETECT)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == START_LIVE_DETECT) {
            when (resultCode) {
                RESULT_OK -> if (null != intent) {
                    val result = intent.getBundleExtra("result")
                    // 错误码, 0检测成功
                    val code = result.getString("code")
                    // 错误信息
                    val msg = result.getString("msg")
                    // 授权有效期
                    val expire = result.getString("expire")
                    // 检测通过,code="0"时返回图片保存路径，完整图像
                    val passImgPath = result.getString("passImgPath")
                    // 检测通过,code="0"时返脸部base64，剪裁后的脸部图像
                    val passFace = result.getString("passFace")
                    Log.e(TAG, "$code : $msg")

                    if(code.equals("0")){
                        mPresenter!!.duibi("ZdS6tcUDkoWfMIuI",System.currentTimeMillis().toString(),
                            MD5Utils.md5("ZdS6tcUDkoWfMIuI"+"&"+System.currentTimeMillis().toString()+"&"+"ZdS6tcUDkoWfMIuIbGTEvwXHBBe35z6o"),name!!,idcard!!,passFace)
                       // ToastUtil.showShort("实名认证成功!")
                       //
                    }else{
                       // ToastUtil.showShort(msg)
                        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
                        var num=   SPUtil.get(SPConfig.NUM,"")
                        if(num!!.toInt()==0){
                            AppManager.getSingleton().finishAllActivity("ShiMingActivity")
                            finish()
                        }else{
                            SPUtil.put(SPConfig.NUM,(num!!.toInt()-1).toString())
                            SPUtil.put(SPConfig.TIME,System.currentTimeMillis().toString())
                        }
                    }
                   // startAc(result)
                }
                else -> {
                }
            }
        }
    }

    fun setData(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        RxBus.getDefault().send(1357)
        AppManager.getSingleton().finishAllActivity("ShiMingActivity")
        finish()
    }

    fun setUI(msg: String?) {
        SPUtil.get(SPConfig.SESSION_ID,"")?.let { mPresenter!!.shiming("1", it,idcard!!,name!!,MD5Utils.md5(idcard!!+name!!+it)) }
    }

    /*   private fun startAc(bundle: Bundle) {
           val intent = Intent()
           val code = bundle.getString("code")
           val msg = bundle.getString("msg")
           intent.putExtra("result", bundle)
           if (Constant.BAD_REASON.DETECT_SUCCESS.equals(code)) {
               intent.setClass(this@FaceActivity, ShuMaiSuccessActivity::class.java)
           } else {
               intent.setClass(this@FaceActivity, ShuMaiFailActivity::class.java)
           }
           startActivity(intent)
       }*/
}