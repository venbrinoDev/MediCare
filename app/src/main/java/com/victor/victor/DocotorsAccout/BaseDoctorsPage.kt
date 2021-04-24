package com.victor.victor.DocotorsAccout

import android.os.Bundle
import android.widget.*
import androidx.annotation.LayoutRes
import com.victor.victor.App.Base.Activity.BaseActivity
import com.victor.victor.App.Models.SearchDoctors
import com.victor.victor.App.util.Constant
import com.victor.victor.R

abstract class BaseDoctorsPage:BaseActivity() {

    lateinit var makeCall: Button;
    lateinit var sendMessage: Button;
    lateinit var backButton: ImageButton;
    lateinit var doctorsImage: ImageView;
    lateinit var doctorsName: TextView;
    lateinit var doctorsOccupation: TextView;
    lateinit var doctorsRating: TextView;
    lateinit var aboutDoctors: TextView;
    lateinit var openOrClose: TextView;
    lateinit var doctorsInfo_: SearchDoctors;
    lateinit var scrollView: ScrollView;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())

        overridePendingTransition(R.anim.push_in_from_right, R.anim.push_out_to_left)

        findAllView()
        registerClickEvent()
        clipDetails(intent.getParcelableExtra<SearchDoctors>(Constant.PASS_INFO))
        registerOthers();
    }

    abstract fun registerOthers()

    abstract fun clipDetails(doctors: SearchDoctors?)

    abstract  fun registerClickEvent();


    @LayoutRes
    abstract  fun getContentView():Int


    abstract fun findAllView();


    override fun onBackPressed() {
        super.onBackPressed()
    }

}