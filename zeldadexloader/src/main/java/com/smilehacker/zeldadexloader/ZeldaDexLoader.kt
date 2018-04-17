package com.smilehacker.zeldadexloader

import android.support.annotation.MainThread
import java.util.*

/**
 * Created by quan.zhou on 2018/4/17.
 */
object ZeldaDexLoader {

    private val mModuleDexes : MutableList<ModuleDescriptor> = ArrayList()

    @MainThread
    fun installModuleDex(module: ModuleDescriptor) {
    }
}