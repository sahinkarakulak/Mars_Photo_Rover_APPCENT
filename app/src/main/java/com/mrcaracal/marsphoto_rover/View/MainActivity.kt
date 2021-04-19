package com.mrcaracal.marsphoto_rover.View

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.mrcaracal.marsphoto_rover.Adapters.MarsAdapter
import com.mrcaracal.marsphoto_rover.Interface.RecyclerviewClickInterface
import com.mrcaracal.marsphoto_rover.Models.Photo
import com.mrcaracal.marsphoto_rover.R
import com.mrcaracal.marsphoto_rover.ViewModel.MarsViewModel
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), RecyclerviewClickInterface {

    private var aracIsmi = "curiosity"
    private lateinit var marsViewModel: MarsViewModel
    private lateinit var marsAdapter: MarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        marsViewModel = ViewModelProvider(this).get(MarsViewModel::class.java)
        setUI()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val str_tab_name = tab.text.toString().toLowerCase()
                aracIsmi = str_tab_name
                getDataFromAPI(str_tab_name)
                recyclerView.smoothScrollToPosition(0)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                //
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
                recyclerView.smoothScrollToPosition(0)
            }
        })

        swipe_refresh_layout.setOnRefreshListener {
            getDataFromAPI(aracIsmi)
            swipe_refresh_layout.isRefreshing = false
        }

    }

    private fun setUI() {
        setActionBar()
        getDataFromAPI(aracIsmi)
    }

    private fun setActionBar() {
        val actionbar: ActionBar? = supportActionBar
        actionbar?.title = "NASA - APPCENT"
        actionbar?.elevation = 4.toFloat()
        actionbar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.design_default_color_primary
                )
            )
        )
    }

    private fun getDataFromAPI(kosul: String) {

        if (kosul.equals("curiosity")) {
            marsViewModel.data.observe(this, Observer { data ->
                marsAdapter = MarsAdapter(photos = data.photos, this)
                recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                recyclerView.adapter = marsAdapter
            })
        } else if (kosul.equals("opportunity")) {
            marsViewModel.data2.observe(this, Observer { data ->
                marsAdapter = MarsAdapter(photos = data.photos, this)
                recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                recyclerView.adapter = marsAdapter
            })
        } else if (kosul.equals("spirit")) {
            marsViewModel.data3.observe(this, Observer { data ->
                marsAdapter = MarsAdapter(photos = data.photos, this)
                recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                recyclerView.adapter = marsAdapter
            })
        } else {
            //
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infilater: MenuInflater = menuInflater
        infilater.inflate(R.menu.menu_top_camera, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fhaz -> {
                Toast.makeText(this@MainActivity, "FHAZ", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.rhaz -> {
                Toast.makeText(this@MainActivity, "RHAZ", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.mast -> {
                Toast.makeText(this@MainActivity, "MAST", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.chemcam -> {
                Toast.makeText(this@MainActivity, "CHENCAM", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.mahli -> {
                Toast.makeText(this@MainActivity, "MAHLI", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.mardi -> {
                Toast.makeText(this@MainActivity, "MARDI", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.navcam -> {
                Toast.makeText(this@MainActivity, "NAVCAM", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.pancam -> {
                Toast.makeText(this@MainActivity, "PANCAM", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.minites -> {
                Toast.makeText(this@MainActivity, "MINITES", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun openWindow(photoClick: Photo) {

        val window = PopupWindow(this)
        val view = layoutInflater.inflate(R.layout.layout_popup, null)
        window.contentView = view
        window.showAtLocation(
            tabs,
            Gravity.CENTER,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )


        val cardMars = view.findViewById<CardView>(R.id.popup_card)
        cardMars.setOnClickListener {
            window.dismiss()
        }


        var pictureMars = view.findViewById<ImageView>(R.id.popup_resim)
        var cekildigiTarihMars = view.findViewById<TextView>(R.id.popup_cekildigi_tarih)
        var aracAdiMars = view.findViewById<TextView>(R.id.popup_arac_adi)
        var kameraAdiMars = view.findViewById<TextView>(R.id.popup_kamera_adi)
        var gorevDurumuMars = view.findViewById<TextView>(R.id.popup_gorev_durumu)
        var firlatmaMars = view.findViewById<TextView>(R.id.popup_firlatma_tarihi)
        var inisMars = view.findViewById<TextView>(R.id.popup_inis_tarihi)

        val image = "${photoClick.img_src}".replace("http", "https")
        Glide.with(this).load(image).into(pictureMars)
        cekildigiTarihMars.text = "Date: "+photoClick.earth_date
        aracAdiMars.text = "Rover Name: "+photoClick.rover.name
        kameraAdiMars.text = "Camera Name: "+photoClick.camera.name
        gorevDurumuMars.text = "Status: "+photoClick.rover.status
        firlatmaMars.text = "Launch Date: "+photoClick.rover.launch_date
        inisMars.text = "Landing Date: "+photoClick.rover.landing_date

        Log.i(
            TAG,
            "openWindow: " + pictureMars + "\n" + cekildigiTarihMars + "\n" + aracAdiMars +
                    "\n" + kameraAdiMars + "\n" + firlatmaMars + "\n" + inisMars
        )

    }
}
