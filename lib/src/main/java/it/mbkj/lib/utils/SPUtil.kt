package it.mbkj.lib.utils

import android.content.SharedPreferences
import it.mbkj.lib.base.App
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class SPUtil {
    private val FILE_NAME = "share_data"

    fun SPUtil() {}
    companion object{
        fun init(extra: String): SharedPreferences.Editor {
            val sp: SharedPreferences = App.INSTANCE!!.getSharedPreferences(extra, 0)
            return sp.edit()
        }

        fun put(key: String?, ob: Any) {
            val editor = init("share_data")
            if (ob is String) {
                editor.putString(key, ob)
            } else if (ob is Int) {
                editor.putInt(key, ob)
            } else if (ob is Boolean) {
                editor.putBoolean(key, ob)
            } else if (ob is Float) {
                editor.putFloat(key, ob)
            } else if (ob is Long) {
                editor.putLong(key, ob)
            } else {
                editor.putString(key, ob.toString())
            }
            SharedPreferencesCompat.apply(editor)
        }

        fun put(key: String?, ob: Any, extra: String) {
            val editor = init(extra)
            if (ob is String) {
                editor.putString(key, ob)
            } else if (ob is Int) {
                editor.putInt(key, ob)
            } else if (ob is Boolean) {
                editor.putBoolean(key, ob)
            } else if (ob is Float) {
                editor.putFloat(key, ob)
            } else if (ob is Long) {
                editor.putLong(key, ob)
            } else {
                editor.putString(key, ob.toString())
            }
            SharedPreferencesCompat.apply(editor)
        }

        operator fun <T> get(key: String?, defaultObject: T): String? {
            val sp: SharedPreferences = App.INSTANCE!!.getSharedPreferences("share_data", 0)
            return if (defaultObject is String) {
                sp.getString(key, defaultObject as String)
            } else (if (defaultObject is Int) {
                sp.getInt(key, (defaultObject as Int))
            } else if (defaultObject is Boolean) {
                sp.getBoolean(key, (defaultObject as Boolean))
            } else if (defaultObject is Float) {
                sp.getFloat(key, (defaultObject as Float))
            } else {
                if (defaultObject is Long) sp.getLong(key, (defaultObject as Long)) else null
            }) as String?
        }

        operator fun <T> get(key: String?, defaultObject: T, extra: String?): String? {
            val sp: SharedPreferences = App.INSTANCE!!.getSharedPreferences(extra, 0)
            return if (defaultObject is String) {
                sp.getString(key, defaultObject as String)
            } else (if (defaultObject is Int) {
                sp.getInt(key, (defaultObject as Int))
            } else if (defaultObject is Boolean) {
                sp.getBoolean(key, (defaultObject as Boolean))
            } else if (defaultObject is Float) {
                sp.getFloat(key, (defaultObject as Float))
            } else {
                if (defaultObject is Long) sp.getLong(key, (defaultObject as Long)) else null
            }) as String?
        }

        fun clear() {
            val sp: SharedPreferences = App.INSTANCE!!.getSharedPreferences("share_data", 0)
            val editor = sp.edit()
            editor.clear()
            SharedPreferencesCompat.apply(editor)
        }

        operator fun contains(key: String?): Boolean {
            val sp: SharedPreferences = App.INSTANCE!!.getSharedPreferences("share_data", 0)
            return sp.contains(key)
        }

        fun getAll(): Map<String?, *>? {
            val sp: SharedPreferences = App.INSTANCE!!.getSharedPreferences("share_data", 0)
            return sp.all
        }

        fun remove(key: String?) {
            val sp: SharedPreferences = App.INSTANCE!!.getSharedPreferences("share_data", 0)
            val editor = sp.edit()
            editor.remove(key)
            SharedPreferencesCompat.apply(editor)
        }

        private object SharedPreferencesCompat {
            private val sApplyMethod = findApplyMethod()
            private fun findApplyMethod(): Method? {
                return try {
                    val clz: Class<*> = SharedPreferences.Editor::class.java
                    clz.getMethod("commit")
                } catch (var1: NoSuchMethodException) {
                    null
                }
            }

            fun apply(editor: SharedPreferences.Editor): Boolean {
                try {
                    if (sApplyMethod != null) {
                        sApplyMethod.invoke(editor)
                        return true
                    }
                } catch (var2: IllegalAccessException) {
                    println(var2)
                } catch (var2: InvocationTargetException) {
                    println(var2)
                } catch (var2: IllegalArgumentException) {
                    println(var2)
                }
                return editor.commit()
            }
    }

    }
}