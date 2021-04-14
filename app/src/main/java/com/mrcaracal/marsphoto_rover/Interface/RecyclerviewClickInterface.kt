package com.mrcaracal.marsphoto_rover.Interface

import com.mrcaracal.marsphoto_rover.Models.Photo

public interface RecyclerviewClickInterface {

    // Kullanacağım verileri burada parametre olarak berlirteceğim
    // Adapter kısmında positionla beraber veri gönderip
    // Activity kısmında gönderilen verileri kullanıcıya göstereceğim.
    //fun openWindow(time: String, link: String)
    fun openWindow(photoClick : Photo)

}