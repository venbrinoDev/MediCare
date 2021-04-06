package com.victor.victor.App.Base.Activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.google.android.material.snackbar.Snackbar
import com.victor.victor.App.util.AttrUtils
import com.victor.victor.App.util.Constant
import com.victor.victor.R

abstract  class BaseActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    fun startNewActivity(name: Class<*>, finish: Boolean = false, data: Bundle? = null) {
        val intent = Intent(this, name)
        intent.putExtra(Constant.EXTRA, data)
        startActivity(intent)
        if (finish) {
            finish()
        }
    }

    fun startNewActivityForResult(name: Class<*>, data: Bundle? = null, requestCode:Int= DEFAULT_REQUEST_CODE) {
        val intent = Intent(this, name)
        intent.putExtra(Constant.EXTRA, data)
        startActivityForResult(intent, requestCode)
    }

    fun getBundleFromIntent(): Bundle? {
        return intent?.extras?.getBundle(Constant.EXTRA)
    }

    fun startNewActivityWithAnimation(name: Class<*>, anchorView: View, data: Bundle? = null) {
        val intent = Intent(this, name)
        startNewActivityWithAnimation(name, intent, anchorView, data)
    }

    fun startNewActivityWithAnimation(name: Class<*>, intent: Intent, anchorView: View, data: Bundle? = null) {
        intent.putExtra(Constant.EXTRA, data)
        intent.setClass(this, name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, anchorView, anchorView.transitionName
                    ?: return
            ).toBundle()
            startActivity(intent, bundle)
        } else {
            startActivity(intent)
        }
    }



    protected open fun onInternetConnectionLost(){

    }
    protected open fun onInternetConnectionGained(){

    }


    fun notifyUser(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun notifyUser(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.setBackgroundColor(AttrUtils.convertAttrToColor(R.attr.colorSecondary, view.context))
        (snackBarLayout.findViewById<View>(R.id.snackbar_text) as TextView)
                .setTextColor(AttrUtils.convertAttrToColor(R.attr.colorOnSecondary, view.context))
        snackBar.show()
    }



    fun showView(view: View, textView: TextView, message: String) {
        textView.text = message

        val bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up)

        view.startAnimation(bottomUp)
        view.visibility = View.VISIBLE
    }

    fun hideView(view: View) {

        val slide = AnimationUtils.loadAnimation(
                this,
                R.anim.up_down
        )
        view.startAnimation(slide)
        view.visibility = View.GONE
    }





    companion object{
        const val DEFAULT_REQUEST_CODE = 112
    }


    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

}