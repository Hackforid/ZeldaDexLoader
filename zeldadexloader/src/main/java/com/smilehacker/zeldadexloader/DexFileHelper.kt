package com.smilehacker.zeldadexloader

import android.content.Context
import com.smilehacker.zeldadexloader.utils.FileUtils
import java.io.File

/**
 * Created by quan.zhou on 2018/4/17.
 */
class DexFileHelper(private val context: Context) {

    private val MODULE_DIR = "zelda_modules"
    private val DEX_FILE_DIR = "dex_file_dir"
    private val ODEX_FILE_DIR = "odex_file_dir"

    private val mModuleDir by lazy { context.getDir(MODULE_DIR, Context.MODE_PRIVATE) }

    fun generateDexFile(module: ModuleDescriptor) : DexFileDescriptor {
        val moduleDexDir = File(File(mModuleDir, module.moduleName), module.version)
        val dexFileDir = File(moduleDexDir, DEX_FILE_DIR)

    }
}