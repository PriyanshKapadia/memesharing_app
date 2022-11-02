package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressbar.visibility = View.VISIBLE
        val sharebutton = findViewById<Button>(R.id.sharebutton)
        val nextbutton = findViewById<Button>(R.id.nextbutton)
        val image = findViewById<ImageView>(R.id.imageView)

        loadmeme()
        sharebutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plaim"
            intent.putExtra(Intent.EXTRA_TEXT, "Hello Sandhya maam,checkout this meme i got from reddit, feel free to share it $image")
            val chooser = Intent.createChooser(intent,"share this meme using...")
            startActivity(chooser)
        }
        nextbutton.setOnClickListener{
            loadmeme()
        }
    }

    fun loadmeme() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val url = response.getString("url")
                val imagememe = findViewById<ImageView>(R.id.imageView)
                Glide.with(this)
                    .load(url)
                    .into(imagememe)

            },
            Response.ErrorListener {
                Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)
    }
}




