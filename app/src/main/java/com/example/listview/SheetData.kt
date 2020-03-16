package com.example.listview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sheet_data.*
import kotlinx.android.synthetic.main.activity_sheet_data.TV_title
import kotlinx.android.synthetic.main.activity_sheet_data.TV_content
import kotlinx.android.synthetic.main.activity_sheet_data.TV_topic

class SheetData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheet_data)

        val bundle: Bundle = intent.extras!!
        val topic = bundle.getString("topic")
        val title = bundle.getString("title")
        val content = bundle.getString("content")
        val source = bundle.getString("source")
        val img = bundle.getInt("img")

        TV_topic.text = topic
        TV_title.text = title
        TV_content.text = content
        TV_source.text = source
        IV_image.setImageResource(img)
    }
}