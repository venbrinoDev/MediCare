package com.victor.victor.Main;

import android.os.Bundle;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.victor.victor.App.Models.DoctorsInfo;
import com.victor.victor.App.Models.SearchDoctors;
import com.victor.victor.App.TopDoctorsAdapter.SearchDoctorsAdapter;
import com.victor.victor.App.TopDoctorsAdapter.TopDoctorsAdapter;
import com.victor.victor.R;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<DoctorsInfo> doctorsInfos;
    ScrollView scrollView;
    BottomSheetDialog bottomSheetDialog;
    RelativeLayout search_doctors_relative;
    CardView  search_doctors_cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        search_doctors_relative = findViewById(R.id.relative_search_doctors);
        search_doctors_cardView = findViewById(R.id.card_search_doctors);


        scrollView = findViewById(R.id.generalScrollView);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        doctorsInfos = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this ,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        init();
        addListOFDoctors();
        initClick();
    }

    void  initClick(){

        search_doctors_cardView.setOnClickListener(view -> {

            initBottomViewForSearch();
        });

        search_doctors_relative.setOnClickListener(view -> {
         initBottomViewForSearch();
        });
    }


    private  void  initBottomViewForSearch(){

        List<SearchDoctors> searchDoctors = new ArrayList<>();
        View getSheetLayout = getLayoutInflater().inflate(R.layout.full_screen_dialog, null);
            bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(getSheetLayout);



        RecyclerView recyclerView_search_doctors = getSheetLayout.findViewById(R.id.recyclerView_search_Doctors);
        recyclerView_search_doctors.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView_search_doctors.setHasFixedSize(true);
        addSearchDoctors(searchDoctors);

        SearchDoctorsAdapter searchDoctorsAdapter = new SearchDoctorsAdapter(this,searchDoctors);
        recyclerView_search_doctors.setAdapter(searchDoctorsAdapter);


        bottomSheetDialog.show();
    }

    private void addSearchDoctors(List<SearchDoctors> searchDoctors) {
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                searchDoctors.add(new SearchDoctors("Dr .Elena George", "Nurse and Doctors", "4.52", R.drawable.nurse, "10:00 Am - 6:00 Am"));
            } else {
                searchDoctors.add(new SearchDoctors("Dr .Precious Bnb", "Therapist", "3.52", R.drawable.nurse, "10:00 Am - 6:00 Am"));

            }
        }
    }


        private void showBottomView(){
        if (bottomSheetDialog!=null)
        bottomSheetDialog.show();
    }
    private void addListOFDoctors(){
        for (int i =0; i<5; i++){
            if (i%2==0){
                doctorsInfos.add(new DoctorsInfo("Dr .Elena George","Nurse and Doctors","4.52",R.drawable.nurse,2));
            }else {
                doctorsInfos.add(new DoctorsInfo("Dr .Precious Bnb","Therapist","3.52",R.drawable.nurse,1));

            }
        }

        loadAdapter();
    }

    private void loadAdapter() {
        TopDoctorsAdapter topDoctorsAdapter  = new TopDoctorsAdapter(this,doctorsInfos);
        recyclerView.setAdapter(topDoctorsAdapter);
    }


    private void init(){
    }

    private  void controlTypeWriting(){

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

}
