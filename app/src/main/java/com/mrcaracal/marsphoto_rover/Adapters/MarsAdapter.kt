package com.mrcaracal.marsphoto_rover.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mrcaracal.marsphoto_rover.Interface.RecyclerviewClickInterface
import com.mrcaracal.marsphoto_rover.Models.Photo
import com.mrcaracal.marsphoto_rover.R
import kotlinx.android.synthetic.main.item_row_photo_and_details.view.*

class MarsAdapter(
    private val photos: List<Photo>,
    recyclerviewClickInterface: RecyclerviewClickInterface ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var click = recyclerviewClickInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_photo_and_details, parent, false)
        return PhotoHolder(v)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is PhotoHolder -> {
                holder.bind(photos[position])

                holder.itemCLickCard.setOnClickListener {
                    //click.openWindow(photos[position].earth_date, photos[position].img_src)
                    click.openWindow(photos[position])
                }

            }
        }
    }

    class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemCLickCard = itemView.item_cardView

        fun bind(photo: Photo) {

            val image = "${photo.img_src}".replace("http", "https")
            Glide.with(itemView.context).load(image).into(itemView.roverImage)
            itemView.roverName.text = photo.rover.name
            itemView.earthDate.text = photo.earth_date
            itemView.cameraName.text = photo.camera.name

        }
    }
}