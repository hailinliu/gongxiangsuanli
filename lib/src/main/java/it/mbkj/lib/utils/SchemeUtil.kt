package it.mbkj.lib.utils

import android.widget.TextView
import com.github.yoojia.inputs.Loader1A
import com.github.yoojia.inputs.Scheme
import com.github.yoojia.inputs.verifiers.EqualsVerifier
import com.github.yoojia.inputs.verifiers.NotEmptyVerifier
import it.mbkj.lib.utils.verifier.*

object SchemeUtil {
    fun pwd(): Scheme {
        return Scheme(PwdVerifier()).msg("密码格式错误")
    }

    fun smsCode(): Scheme {
        return Scheme(SmsCodeVerifier()).msg("验证码错误")
    }

    fun emailPhone(): Scheme {
        return Scheme(EmailPhoneVerifier()).msg("手机号码或邮箱格式错误")
    }

    fun phone(): Scheme {
        return Scheme(PhoneVerifier()).msg("手机号码格式错误")
    }

    fun email(): Scheme {
        return Scheme(EmailVerifier()).msg("邮箱格式错误")
    }

    fun notEmpty(textView: TextView): Scheme {
        return Scheme(NotEmptyVerifier()).msg(textView.hint.toString())
    }

    fun checkbox(textView: TextView): Scheme {
        return Scheme(CheckBoxVerifier()).msg(textView.hint.toString())
    }

    /**
     * 输入内容与加载器的内容相同
     *
     * @param lazyLoader 相同内容延迟加载器
     * @return Scheme
     */
    fun equalsPwd(lazyLoader: Loader1A<String?>?): Scheme {
        return Scheme(EqualsVerifier(lazyLoader)).msg("两次密码输入不一致")
    }
}