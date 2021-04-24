package com.victor.victor.showmap.Base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.victor.victor.R
import com.victor.victor.showmap.getPalces.PlacesViewModel

abstract  class BaseFragmentActivity :FragmentActivity()  {

   lateinit var viewModel: PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (enableFullScreen()){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }

        if (showTransition()){
            overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left)
        }

        setContentView(getLayoutId())





    }


    abstract  fun showTransition():Boolean

    abstract fun enableFullScreen():Boolean

    @LayoutRes
    abstract  fun getLayoutId():Int
}