package com.smilehacker.zeldadexloader

import java.io.File

/**
 * Created by quan.zhou on 2018/4/17.
 */
data class DexFileDescriptor(val moduleName: String, val dexFile: File, val odexFile: File)