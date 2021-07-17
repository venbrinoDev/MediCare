package com.victor.victor.showmap.model

import com.google.android.gms.maps.model.LatLng

data class MapData (val hospitalName:String,val hospitalAddress:String, val hospitalImage:String, val aboutHospital:String,
val hospitalNumber:String, val area:LatLng
                    ){

}
