package com.victor.victor.SplashScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.victor.victor.App.Base.Activity.BaseActivity
import com.victor.victor.App.util.Constant
import com.victor.victor.App.util.SharedPrefManager
import com.victor.victor.R

class SplashScreen :BaseActivity() {

    private lateinit var sharedPrefManager:SharedPrefManager ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen);
       sharedPrefManager= SharedPrefManager(this)

        splashScreenCountDown(1000);
    }


    private fun splashScreenCountDown(countDown:Long){
        Handler(Looper.myLooper()!!).postDelayed( {
          if (justInstalledApp()){
              saveStartState();
              showOnBoarding();
          }else{
              showMainScreen();
          }
        },countDown)

    }

    private fun showMainScreen() {

    }

    private fun showOnBoarding(){

    }

    private fun saveStartState()   = sharedPrefManager.saveBoolean(Constant.JUST_INSTALLED,false)

    private fun  justInstalledApp():Boolean= sharedPrefManager.getBoolean(Constant.JUST_INSTALLED,true)

}