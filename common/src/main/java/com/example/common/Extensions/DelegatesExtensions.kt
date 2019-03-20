package cn.com.eado.smartpatrol.extensions

import android.content.Context
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

//利用委托特性实现sharedPreference的自动存取
class Preference<T>(val context: Context, val key: String, val default:T) : ReadWriteProperty<Any?, T>{

    val pref by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(key,default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(key, value)
    }

    private fun <T> findPreference(key: String, default: T): T = with(pref){
        val res:Any = when(default){
            is Long -> getLong(key, default)
            is String -> getString(key, default)
            is Int -> getInt(key, default)
            is Boolean -> getBoolean(key, default)
            is Float -> getFloat(key, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as T
    }

    private fun putPreference(key: String, default: T)  = with(pref.edit()) {
        when (default) {
            is Long -> putLong(key, default)
            is String -> putString(key, default)
            is Int -> putInt(key, default)
            is Boolean -> putBoolean(key, default)
            is Float -> putFloat(key, default)
            else -> throw IllegalArgumentException("This  ttype can be saved into Pref erences")
        }.apply()
    }
}





object DelegatesExt{
    fun <T:Any> preference(context: Context, name: String, default:	T) = Preference(context, name, default)
}
