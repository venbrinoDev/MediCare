package com.victor.victor.SplashScreen

import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.ChangeImageTransform
import android.transition.Explode
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.victor.victor.App.Base.Activity.BaseActivity
import com.victor.victor.App.util.Constant
import com.victor.victor.App.util.SharedPrefManager
import com.victor.victor.R
import com.victor.victor.introscreen.IntroActivity

class SplashScreen :BaseActivity() {

    private lateinit var sharedPrefManager:SharedPrefManager ;

    private  lateinit var view:View;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window){
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            enterTransition =Explode()
            exitTransition = Explode()
            sharedElementEnterTransition = ChangeImageTransform()
            sharedElementExitTransition =ChangeImageTransform()
        }
        sharedPrefManager= SharedPrefManager(this)


        setContentView(R.layout.activity_splash_screen);

        view = findViewById<View>(R.id.intro)
        val introScreen = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        view.animation = introScreen

        splashScreenCountDown(1000);
    }


    private fun splashScreenCountDown(countDown: Long){
        Handler(Looper.myLooper()!!).postDelayed({
            if (justInstalledApp()) {
                saveStartState();
                showOnBoarding();
            } else {
                showMainScreen();
            }
        }, countDown)

    }

    private fun showMainScreen() {
        val options = ActivityOptions.makeSceneTransitionAnimation(this,view,"robot")
        Intent(this, IntroActivity::class.java).apply { startActivity(this, options.toBundle());        }
    }

    private fun showOnBoarding(){
        val options = ActivityOptions.makeSceneTransitionAnimation(this,view,"robot")
        Intent(this, IntroActivity::class.java).apply { startActivity(this, options.toBundle());        }

    }

    private fun saveStartState()   = sharedPrefManager.saveBoolean(Constant.JUST_INSTALLED, false)

    private fun  justInstalledApp():Boolean= sharedPrefManager.getBoolean(
        Constant.JUST_INSTALLED,
        true
    )

}