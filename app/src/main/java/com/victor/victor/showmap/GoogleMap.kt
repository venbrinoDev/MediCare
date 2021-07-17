package com.victor.victor.showmap

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import com.victor.victor.R
import com.victor.victor.showmap.Base.BaseFragmentActivity
import com.victor.victor.showmap.getPalces.PlaceViewModelFactory
import com.victor.victor.showmap.getPalces.PlacesViewModel
import com.victor.victor.showmap.model.MapData


class GoogleMap : BaseFragmentActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener {
    private lateinit var googleMap: GoogleMap;
    private lateinit var supportMapFragment: SupportMapFragment;
    private lateinit var bottomSheetDialog: BottomSheetDialog;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)

        val repository = Repository()
        val viewModelFactory = PlaceViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlacesViewModel::class.java)
    }

    override fun initClicks() {
        backButton.setOnClickListener {
            onBackPressed()
        }
    }


    override fun onMapReady(map: GoogleMap) {
        map.mapType = GoogleMap.MAP_TYPE_NORMAL;
        map.isTrafficEnabled = true


        var sydney = LatLng(5.367434902738251, 6.957868677256818);


        for(hospital in  getHospital()){
            sydney = hospital.area
            val markerOption = map.addMarker(
                MarkerOptions().position(sydney)
                    .title(hospital.hospitalName)
            )
            markerOption.tag  = hospital

        }

        val zoomLevel = 10.0f //This goes up to 21

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))

        map.setOnMarkerClickListener(this)

    }


    override fun onMarkerClick(marker: Marker?): Boolean {

        val mapData = marker?.tag as MapData
        initBottomViewForSearch(mapData = mapData)

        return false
    }


    private fun initBottomViewForSearch(mapData: MapData?) {
        val getSheetLayout = layoutInflater.inflate(getBottomSheetLayout(), null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(getSheetLayout)


        bottomSheetDialog.also {
            it.findViewById<View>(R.id.bookAppointment).also { book ->
                book?.setOnClickListener {
                    makeCall(mapData!!.hospitalNumber)
                }
            }
            it.findViewById<TextView>(R.id.hospital_name).also { hospitalName ->
                hospitalName?.text = mapData?.hospitalName
            }


            it.findViewById<TextView>(R.id.hospital_address).also { hospitalAddress ->
                hospitalAddress?.text = mapData?.hospitalAddress

            }


            it.findViewById<TextView>(R.id.hospital_about).also { aboutSection ->
                aboutSection?.text = mapData?.aboutHospital
            }

            it.findViewById<ImageView>(R.id.hospital_image).also { hospitalImage->
                Picasso.get().load(mapData?.hospitalImage).fit().placeholder(R.drawable.map_hospital_icon).into(
                    hospitalImage
                );
            }


        }

        bottomSheetDialog.show()



    }


    private fun getHospital():ArrayList<MapData>{
        val list = ArrayList<MapData>()
        list.add(
            MapData(
                "Regions Stroke and Neuroscience Hospital, Owerri",
                "KM 17 Port-Hacourt Road Owerri - Port Harcourt Express Road. (After Obinze, Military Barracks, Mgbirichi",
                "https://lh5.googleusercontent.com/p/AF1QipPpzvwkF2WO9Vquns93PGJOZQA7JGTj2TMOPjNx=w540-h585-p-k-no",
                "At the Regions Stroke and Neuroscience Hospital, health care is topnotch. The very first time I walked into the hospital, the ambience was wonderful. Clean, neat, with lovely exceptional people. Facilities in the hospital are world class. The neuro rehabilitation center has occupational therapy and speech therapy in addition to physical therapy.  They have a special care for seizures and epilepsy that is effective. Handling strokes is a specialty of the hospital too. I can say this is indeed a world class hospital here in Nigeria. Kudos!",
                "07047000011",
                LatLng(5.367434902738251, 6.957868677256818)
            )
        )


        list.add(
            MapData(
                hospitalAddress = "Awaka, Owerri",
                hospitalImage = "https://lh3.googleusercontent.com/p/AF1QipP1gklfMsXPWmL5sn5Qrr_-Ay63qz3CFznjNxZu=h400-no",
                hospitalName = "Medical City Hospital and Dialysis Center Owerri",
                hospitalNumber = "08136658349",
                aboutHospital = " This hospital offers affordable and subsidized services considering the fact that it is one of the highly equipped hospitals in Nigeria.",
                area = LatLng(5.477697832591259, 7.082664404896688)
            )
        );

        list.add(
            MapData(
                hospitalAddress = "Umuguma Road, New Owerri, Owerri",
                hospitalImage = "https://lh5.googleusercontent.com/8IwKIE80Rg5GNmdUfg8mCmNI5ToVq1Oq15UHdxz639oJ00JHqJmrWsm_qEQ3CpHFIA=w426-h240-k-no",
                hospitalName = "General Hospital Umuguma",
                hospitalNumber = "07047000011",
                aboutHospital = "Very popular for its long existence and quality health care services.",
                area = LatLng(5.487448620667816, 7.006594585769059)
            )
        );
        list.add(
            MapData(
                hospitalAddress = "93 Douglas Rd, Owerri",
                hospitalImage = "https://lh4.googleusercontent.com/lLFtW4ShPLRU_9njN6vn_qO1r1tg5nhH3H4bXPzWbMwp3RgktxUZ7kVb-a4jw50W=w426-h240-k-no",
                hospitalName = "Uchendu Hospital",
                hospitalNumber = "+2347068604702",
                aboutHospital = "Very popular for its long existence and quality health care services.",
                area = LatLng(5.481980605887112, 7.032515452879252)
            )
        );
        list.add(
            MapData(
                hospitalAddress = "5b Okigwe Rd, opp. New fire service, Owerri",
                hospitalImage = "https://lh5.googleusercontent.com/p/AF1QipNrxxNnuT3bpGeH0Ba2sZR56MxC_-zA0PYhLoU5=w408-h448-k-no",
                hospitalName = "Mother Healthcare Diagnostics Hospital\n",
                hospitalNumber = "08187939934",
                aboutHospital = "Very popular for its long existence and quality health care services.",
                area = LatLng(5.494796186386188, 7.029253888293683)
            )
        );

        //from here

        list.add(
            MapData(
                hospitalAddress = "Owerri",
                hospitalImage = "https://lh5.googleusercontent.com/p/AF1QipOSwwU-ZCXhipIQx0DfAGvGcNH274I97dTZXLl5=w423-h240-k-no",
                hospitalName = "Amanda Hospital",
                hospitalNumber = "08187939934",
                aboutHospital = "One of the best",
                area = LatLng(5.51196863205271, 7.036463665084842)
            )
        );

        list.add(
            MapData(
                hospitalAddress = "Port Harcourt Rd, Mberichi",
                hospitalImage = "https://lh5.googleusercontent.com/p/AF1QipN0J1zopMLLnZNNVxuJOy8Mum7DaNqO2-n5N5yU=w408-h544-k-no",
                hospitalName = "St Joseph Eye Hospital",
                hospitalNumber = "08187939934",
                aboutHospital = "I have known St Joseph Eye Hospital since inception in 2009, I was pleased today with the level of improvements that have taken place.",
                area = LatLng(5.354524260235871, 6.946885348247958)
            )
        );


        return  list
    }



    private fun  makeCall(number: String){
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$number")
        }

        if (intent.resolveActivity(packageManager) !=null){
            startActivity(intent)
        }
    }

    override fun showTransition(): Boolean =true

    override fun enableFullScreen(): Boolean= false

    override fun getLayoutId(): Int =R.layout.google_map

    private fun getBottomSheetLayout():Int = R.layout.map_hospital_indocator

}