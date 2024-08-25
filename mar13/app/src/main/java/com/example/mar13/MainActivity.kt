package com.example.mar13

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mar13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    var sPref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sPref = this.getSharedPreferences("TABLE", Context.MODE_PRIVATE)

        bind.btSave.setOnClickListener {
            var editor = sPref?.edit()
            val id = bind.edID.text.toString()
            //editor?.putInt("id_key", id.toInt())
            //editor?.putString("name_key", bind.edName.text.toString())
            editor?.putString(id, bind.edName.text.toString())
            editor?.apply()
        }

        bind.bFind.setOnClickListener {
            val findIDValue = bind.edID.text.toString()
            //val findIDValue = sPref?.getInt("id_key", 0)
            //val findNameValue = sPref?.getString("name_key", "defaultName")
            val findNameValue = sPref?.getString(findIDValue, "defaultName")
            if (findNameValue == "defaultName") {
                bind.tvFindID.text = "Id не найден"
                bind.tvFindName.text = "Необходима регистрация"
            } else {
                bind.tvFindID.text = findIDValue.toString()
                bind.tvFindName.text = findNameValue
            }
        }

        bind.btClear.setOnClickListener {
            var editor = sPref?.edit()
            editor?.clear()
            editor?.apply()
            bind.tvFindID.text = ""
            bind.tvFindName.text = ""
        }



    }



}