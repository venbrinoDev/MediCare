package com.victor.victor.showmap.Base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.victor.victor.R
import com.victor.victor.showmap.getPalces.PlacesViewModel

abstract  class BaseFragmentActivity :FragmentActivity()  {

   lateinit var viewModel: PlacesViewModel
   lateinit var backButton: ImageButton

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
        intiViews();
        initClicks()
    }

    abstract fun initClicks()

    private fun intiViews(){
         backButton = findViewById(R.id.backButton);

     }


    protected   fun showToast(message:String ,length:Int =1){

        when (length) {
            1 -> Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        }

    }

    abstract  fun showTransition():Boolean

    abstract fun enableFullScreen():Boolean

    @LayoutRes
    abstract  fun getLayoutId():Int
}