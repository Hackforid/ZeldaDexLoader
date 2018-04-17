package com.smilehacker.zeldadexloader

import dalvik.system.DexFile
import java.io.File
import java.lang.reflect.Field

/**
 * Created by quan.zhou on 2018/4/17.
 */
class LinkClassLoader private constructor() : ClassLoader(LinkClassLoader.SYSTEM_CLASSLOADER) {

    companion object {

        private val APP_CLASSLOADER: ClassLoader
        private val SYSTEM_CLASSLOADER: ClassLoader
        private val CLASSLOADER_PARENT_FIELD: Field
        private val mInstance: LinkClassLoader by lazy { LinkClassLoader() }

        init {
            try {
                APP_CLASSLOADER = LinkClassLoader::class.java.classLoader
                CLASSLOADER_PARENT_FIELD = ClassLoader::class.java.getDeclaredField("parent")
                CLASSLOADER_PARENT_FIELD.isAccessible = true
                SYSTEM_CLASSLOADER = CLASSLOADER_PARENT_FIELD.get(APP_CLASSLOADER) as ClassLoader
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }

        fun instance() : LinkClassLoader {
            return mInstance
        }
    }

    private var mDexFiles : Array<DexFile>? = null

    init {
        install()
    }

    private fun install() {
        try {
            CLASSLOADER_PARENT_FIELD.set(APP_CLASSLOADER, this)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun addDex(dexFile: File, odexFile: File) {
        val dexList = arrayOfNulls<DexFile>((mDexFiles?.size ?: 0) + 1)
        mDexFiles?.forEachIndexed { index, dexFile ->
            dexList[index] = dexFile
        }
        dexList[dexList.size] = DexFile.loadDex(dexFile.absolutePath, odexFile.absolutePath, 0)
        mDexFiles = dexList as Array<DexFile>
    }

    override fun findClass(name: String?): Class<*> {
        mDexFiles?.forEach {
            val targetClass = it.loadClass(name, APP_CLASSLOADER)
            if (targetClass != null) {
                return targetClass
            }
        }
        return super.findClass(name)
    }

}