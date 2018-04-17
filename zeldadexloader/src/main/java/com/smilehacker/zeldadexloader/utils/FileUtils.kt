package com.smilehacker.zeldadexloader.utils

import java.io.File

/**
 * Created by quan.zhou on 2018/4/17.
 */
object FileUtils {

    @JvmStatic
    fun makeDirs(dir: File) : Boolean {
        if (dir.exists()) {
            if (dir.isDirectory) {
                return true
            } else {
                if (!dir.delete()) {
                    return false
                }
            }
        }

        return dir.mkdirs()
    }
}