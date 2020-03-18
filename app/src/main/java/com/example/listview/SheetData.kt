package com.example.listview

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sheet_data.*

class SheetData : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var database = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheet_data)

        val bundle: Bundle = intent.extras!!
        val topic = bundle.getString("topic")
        val title = bundle.getString("title")
        val content = bundle.getString("content")
        val source = bundle.getString("source")
        val img = bundle.getInt("img")

        val stars = findViewById<View>(R.id.RT_rateUs) as RatingBar
        val sButton = findViewById<View>(R.id.BT_rateUs) as Button

        mAuth = FirebaseAuth.getInstance()
        myRef = database.getReference("users")

        TV_topic.text = topic
        TV_title.text = title
        TV_content.text = content
        TV_source.text = source
        IV_image.setImageResource(img)

        sButton.setOnClickListener(View.OnClickListener {
            val user = mAuth!!.getCurrentUser()
            var udata = UserData()
            with(udata) {
                userRating = stars.rating
            }
            myRef!!.child(user!!.uid).push().setValue(udata)
            Toast.makeText(this, "Puntuación enviada! " + stars.rating.toString(),Toast.LENGTH_SHORT).show()
        })
    }

    fun bt_mail_clicked (view: View) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("wbenitez16@utalca.cl"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contacto App Cuidado Parental")

        startActivity(Intent.createChooser(intent, "Email via..."))
    }

    fun bt_wsp_clicked (view: View) {
        val pm = packageManager
        try {
            val waIntent = Intent(Intent.ACTION_SEND)
            waIntent.type = "text/plain"
            val text = "YOUR TEXT HERE"
            val info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
            //Check if package exists or not. If not then code in catch block will be called
            waIntent.setPackage("com.whatsapp")
            waIntent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(waIntent, "Share with"))
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show()
        }
    }
}

