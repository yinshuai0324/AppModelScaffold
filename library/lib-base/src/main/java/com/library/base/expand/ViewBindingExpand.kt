package com.library.base.expand

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 09:37
 * 作用描述：ViewBinding 扩展类
 */

/**
 * Activity扩展binding方法
 */
inline fun <reified VB : ViewBinding> Activity.bindView() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

inline fun <reified VB : ViewBinding> Dialog.bindView() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB


/**
 * Fragment扩展binding方法
 */
inline fun <reified VB : ViewBinding> Fragment.bindView() =
    FragmentBindingDelegate(VB::class.java)

inline fun Fragment.doOnDestroyView(crossinline block: () -> Unit) =
    viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroyView() {
            block.invoke()
        }
    })


/**
 * Fragment扩展binding方法
 */
class FragmentBindingDelegate<VB : ViewBinding>(
    private val clazz: Class<VB>
) : ReadOnlyProperty<Fragment, VB> {

    private var binding: VB? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (binding == null) {
            binding = clazz.getMethod("bind", View::class.java)
                .invoke(null, thisRef.requireView()) as VB
            thisRef.doOnDestroyView { binding = null }
        }
        return binding!!
    }
}