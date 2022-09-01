package io.multimoon.colorful

import android.app.Activity
import android.os.Bundle
import androidx.annotation.RequiresApi

@RequiresApi(21)
open class CActivity : Activity(), CThemeInterface {
    
    override var themeString: String = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleOnCreate(this, savedInstanceState, BaseTheme.THEME_MATERIAL)
    }
    
    override fun onResume() {
        super.onResume()
        handleOnResume(this)
    }
}