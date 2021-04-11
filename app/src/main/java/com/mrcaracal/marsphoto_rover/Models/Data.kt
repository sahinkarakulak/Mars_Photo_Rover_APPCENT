package com.mrcaracal.marsphoto_rover.Models

data class Data(
    val photos: ArrayList<Photo>
)

data class Photo(
    val img_src: String,
    val earth_date: String,
    val rover: Rover,
    val camera: Camera
)

data class Rover(
    val name: String,
    var status: String,
    var launch_date: String,
    var landing_date: String
)

data class Camera(
    val name: String
)
