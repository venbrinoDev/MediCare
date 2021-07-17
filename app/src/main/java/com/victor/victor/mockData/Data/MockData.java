package com.victor.victor.mockData.Data;

import com.victor.victor.App.Models.SearchDoctors;
import com.victor.victor.R;
import com.victor.victor.showmap.model.MapData;

import java.util.ArrayList;
import java.util.List;

public class MockData {



    public static List<SearchDoctors> getSearchDoctors(){
        ArrayList<SearchDoctors> metadata = new ArrayList<>();
        metadata.add(new SearchDoctors("Dr .Chris Simon", "Gynecologist", "+23490629281", R.drawable.doc_one, "6:00 Am - 6:00 Pm","4.35","" +
                "Dr .Chris Simon is a well trained doctor.He has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Silas Ola", "Obstetrician", "+23490629281", R.drawable.doc_two, "5:00 Am - 6:00 Pm","4.59","" +
                "Dr .Silas Ola is a well trained doctor.He has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Elena Precious", "MidWife", "+23490629281", R.drawable.doc_three, "4:00 Am - 10:00 Pm","4.78",
                "Dr .Elena Precious is a well trained doctor.She has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Mike Elas", "Gynecologist", "+23490629281", R.drawable.doc_four, "10:00 Am - 6:00 Pm","4.12",
                "Dr.Mike Elas is a well trained doctor.He has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Melas Hope", " Gynecologist", "+23490629281", R.drawable.doc_five, "10:00 Am - 6:00 Pm","5.00","" +
                "Dr .Melas Hope is a well trained doctor.He has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Ulas Hope", " MildWife", "+23490629281", R.drawable.doc_six, "10:00 Am - 6:00 Pm","5.00","" +
                "Dr .Ulas Hope is a well trained doctor.She has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Paul Chuks", " Obstetrician", "+23490629281", R.drawable.doc_seven, "10:00 Am - 6:00 Pm","5.00","" +
                "Dr .Paul Chuks is a well trained doctor.He has gone through the necessary certification."));

        return metadata;

    }

    public static List<MapData> getMapData(){
        return  null;
    }

    public static List<SearchDoctors> getTopDoctors(){
        ArrayList<SearchDoctors> metadata = new ArrayList<>();
        metadata.add(new SearchDoctors("Dr .Chris Simon", "Gynecologist", "+23490629281", R.drawable.doc_one, "6:00 Am - 6:00 Pm","4.35","" +
                "Dr .Chris Simon is a well trained doctor.He has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Silas Ola", "Obstetrician", "+23490629281", R.drawable.doc_two, "5:00 Am - 6:00 Pm","4.59","" +
                "Dr .Silas Ola is a well trained doctor.He has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Elena Precious", "MidWife", "+23490629281", R.drawable.doc_three, "4:00 Am - 10:00 Pm","4.78",
                "Dr .Elena Precious is a well trained doctor.She has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Paul Elas", "Gynecologist", "+23490629281", R.drawable.doc_four, "10:00 Am - 6:00 Pm","4.12",
                "Dr.Paul Elas is a well trained doctor.He has gone through the necessary certification."));
        metadata.add(new SearchDoctors("Dr .Paul Chuks", " Obstetrician", "+23490629281", R.drawable.doc_seven, "10:00 Am - 6:00 Pm","5.00","" +
                "Dr .Paul Chuks is a well trained doctor.He has gone through the necessary certification."));

        return metadata;
    }

}
