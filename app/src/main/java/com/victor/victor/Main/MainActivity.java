package com.victor.victor.Main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.victor.victor.App.Base.Activity.BaseActivity;
import com.victor.victor.App.Models.SearchDoctors;
import com.victor.victor.App.Adapters.SearchDoctorsAdapter;
import com.victor.victor.App.Adapters.TopDoctorsAdapter;
import com.victor.victor.App.inteface.ClickItem;
import com.victor.victor.App.util.Constant;
import com.victor.victor.DocotorsAccout.DoctorsPageActivity;
import com.victor.victor.Main.searchDocotrs.SearchImplement;
import com.victor.victor.R;
import com.victor.victor.TipsAndGuide.TipsAndGuide;
import com.victor.victor.about_us.About_us;
import com.victor.victor.mockData.Data.MockData;
import com.victor.victor.showmap.GoogleMap;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class MainActivity extends BaseActivity implements ClickItem {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ScrollView scrollView;
    BottomSheetDialog bottomSheetDialog;
    RelativeLayout search_doctors_relative,talk_to_Elena;
    CardView  search_doctors_cardView,nearby_hospital,tips_and_guide,about_us;
    List<SearchDoctors> searchDoctors;
    List<SearchDoctors> topDoctors;
    SearchDoctorsAdapter searchDoctorsAdapter;
    Intent detailsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());


        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        search_doctors_relative = findViewById(R.id.relative_search_doctors);
        search_doctors_cardView = findViewById(R.id.card_search_doctors);
        nearby_hospital = findViewById(R.id.nearbyHospital);
        tips_and_guide = findViewById(R.id.tips_and_guide);
        about_us = findViewById(R.id.about_us);
        searchDoctors = MockData.getSearchDoctors();
        topDoctors =  MockData.getTopDoctors();

        detailsActivity = new Intent(this, DoctorsPageActivity.class);


        talk_to_Elena = findViewById(R.id.talk_to_Elena);
        scrollView = findViewById(R.id.generalScrollView);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);


        linearLayoutManager = new LinearLayoutManager(this ,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        loadAdapter();
        initClick();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        finishAffinity();
        overridePendingTransition(R.anim.push_in_from_left,R.anim.push_out_to_right);
    }

    void  initClick(){


        about_us.setOnClickListener(view -> {
            About_us about_us = new About_us();
            about_us.show(getSupportFragmentManager(),"about_us");
        });


        tips_and_guide.setOnClickListener(view -> {
            if (isNetworkAvailable()){
                Intent intent = new Intent(MainActivity.this, TipsAndGuide.class);
                startActivity(intent);
                return;
            }

            Toast.makeText(MainActivity.this,"Check Network Connectivity",Toast.LENGTH_LONG).show();

        });

        talk_to_Elena.setOnClickListener(view -> {
                    gotoMessengerNow();
        });


        search_doctors_cardView.setOnClickListener(view -> {

            initBottomViewForSearch();
        });


        search_doctors_relative.setOnClickListener(view -> {
         initBottomViewForSearch();
        });

        nearby_hospital.setOnClickListener(view -> {
            if (isNetworkAvailable()){
                Intent intent = new Intent(MainActivity.this, GoogleMap.class);
                startActivity(intent);
                return;
            }

            Toast.makeText(MainActivity.this,"Check Network Connectivity",Toast.LENGTH_LONG).show();

        });

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void gotoMessengerNow(){
        Uri uri = Uri.parse("http://m.me/101823901366644");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            try {

                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this,"Please Install messenger On your device",Toast.LENGTH_LONG).show();
            }
    }

    private  void  initBottomViewForSearch(){

        View getSheetLayout = getLayoutInflater().inflate(R.layout.full_screen_dialog, null);
            bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(getSheetLayout);


        EditText editText = getSheetLayout.findViewById(R.id.search_editText);
        implementSearch(editText);


        RecyclerView recyclerView_search_doctors = getSheetLayout.findViewById(R.id.recyclerView_search_Doctors);
        recyclerView_search_doctors.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView_search_doctors.setHasFixedSize(true);

      searchDoctorsAdapter = new SearchDoctorsAdapter(this,searchDoctors,this);
        recyclerView_search_doctors.setAdapter(searchDoctorsAdapter);


        bottomSheetDialog.show();
    }

    void setAdapterChange(CharSequence charSequence){
        List<SearchDoctors> stableInfo = searchDoctors;

        if (charSequence.length()<1){
            searchDoctorsAdapter.refreshList(stableInfo);
        }else{
            searchDoctorsAdapter.refreshList(filterInfo(charSequence,stableInfo));
        }

    }

    private List<SearchDoctors> filterInfo( CharSequence charSequence,List<SearchDoctors> searchDoctors){
        List<SearchDoctors> filteredInfo = new ArrayList<>();
        for (SearchDoctors info :searchDoctors){
            if (info.getDoctorsOccupations().trim().toLowerCase().contains(charSequence.toString().trim().toLowerCase())
            ||info.getDoctorsName().trim().toLowerCase().contains(charSequence.toString().trim().toLowerCase())){
                filteredInfo.add(info);
            }
        }

        return filteredInfo;
    }

    private void implementSearch(EditText editText) {
        SearchImplement  searchImplement = new SearchImplement() {
            @Override
            protected void textChange(CharSequence charSequence, int i, int i1, int i2) {
                  setAdapterChange(charSequence);
            }
        };

        editText.addTextChangedListener(searchImplement);
    }


    private void loadAdapter() {
        TopDoctorsAdapter topDoctorsAdapter  = new TopDoctorsAdapter(this,topDoctors,this);
        recyclerView.setAdapter(topDoctorsAdapter);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @Override
    protected void onPause() {
        super.onPause();

    }



    @Override
    public void clickSearchDoctors(@NotNull SearchDoctors searchDoctors) {
        detailsActivity.putExtra(Constant.PASS_INFO,searchDoctors);
        startActivity(detailsActivity);

    }
}
