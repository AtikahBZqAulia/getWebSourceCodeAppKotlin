package com.example.getwebpagesourcecode2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.koushikdutta.ion.Ion
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val changeHttp = findViewById<TextView>(R.id.change_http)
        val getSourceCode = findViewById<TextView>(R.id.get_source_result)
        val userInput = findViewById<EditText>(R.id.user_input)
        val btnGet = findViewById<Button>(R.id.button_get)
        changeHttp.setOnClickListener{
            val popupMenu = PopupMenu(this, changeHttp)
            popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.http_click -> {
                        changeHttp.setText("http://")
                    }
                    R.id.https_click -> {
                        changeHttp.setText("https://")
                    }
                }
                true
            }
            popupMenu.show()
        }
        btnGet.setOnClickListener {
            Ion.with(applicationContext).load(userInput.toString()).asString().setCallback { e, result -> getSourceCode.setText(result) }
        }
        try {
            var url: URL? = null
            url = URL(userInput.getText().toString())
            val conn: URLConnection = url.openConnection()
            val reader = BufferedReader(
                    InputStreamReader(conn.getInputStream()))
            var line: String? = ""
            while (reader.readLine().also { line = it } != null) {
                getSourceCode.append(line)
            }
        } catch (e: Exception) {
            Log.e("ERR", e.message.toString())
        }
    //        val url = URL("http://www.android.com/")
//        val urlConnection = url.openConnection() as HttpURLConnection
//
//        try {
//            val text = urlConnection.inputStream.bufferedReader().readText()
//            Log.d("UrlTest", text)
//        } finally {
//            urlConnection.disconnect()
//        }
    }
    fun getSourceCode() {
        val cek = ""
    }
}