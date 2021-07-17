package com.victor.victor.DocotorsAccout

import android.content.Intent
import android.net.Uri
import android.view.View
import com.victor.victor.App.Models.SearchDoctors
import com.victor.victor.R
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class DoctorsPageActivity: BaseDoctorsPage() {



    override fun findAllView() {
        makeCall = findViewById(R.id.call)
        backButton = findViewById(R.id.backButton)
        doctorsImage = findViewById(R.id.doctorsImage)
        doctorsName = findViewById(R.id.doctorsName)
        doctorsOccupation=findViewById(R.id.doctorsOccupation)
        aboutDoctors = findViewById(R.id.aboutDocotors)
        openOrClose = findViewById(R.id.openOrClose)
        scrollView = findViewById(R.id.generalScrollView);
        doctorsRating = findViewById(R.id.doctorsNumber)
    }

    override fun registerOthers() {
        OverScrollDecoratorHelper.setUpOverScroll(scrollView)
           scrollView.post {
              scrollView.fullScroll(View.FOCUS_DOWN)
           }
    }

    override fun clipDetails(doctors: SearchDoctors?) {
   if (doctors==null){
       finish()
      }
        doctorsInfo_ = doctors!!;

        doctorsImage.setImageResource(doctors.doctorsImage)
        doctorsOccupation.text = doctors.doctorsOccupations
        doctorsName.text = doctors.doctorsName
        doctorsRating.text = doctors.rating
        aboutDoctors.text = doctors.about

    }


    override fun registerClickEvent() {
        backButton.setOnClickListener {
            onBackPressed()
        }
        makeCall.setOnClickListener {

            makeCall(doctorsInfo_.doctorsNumber!!)
        }

    }


   private fun  makeCall(number: String){
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$number")
        }

        if (intent.resolveActivity(packageManager) !=null){
                startActivity(intent)
        }
    }

   private fun composeMmsMessage(message: String, number: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type ="text/plain"
            data = Uri.parse("smsto:$number")  // This ensures only SMS apps respond
            putExtra("sms_body", message)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


    override fun getContentView(): Int = R.layout.profile_book_appointment;

}