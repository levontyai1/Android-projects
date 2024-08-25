package com.example.kopmas

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.example.kopmas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {
    lateinit var bind: ActivityMainBinding
    var manager: SensorManager? = null
    var current_deegre: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager



    }

    override fun onPause() {
        super.onPause()
        manager?.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        manager?.registerListener(this,
            manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val deagre: Int = event?.values?.get(0)?.toInt()!!
        bind.textView.text = deagre.toString()

        val rotateAnimation = RotateAnimation(
            current_deegre.toFloat(),(-deagre).toFloat(),
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        rotateAnimation.duration = 210
        rotateAnimation.fillAfter = true
        current_deegre = -deagre
        bind.imageDina.startAnimation(rotateAnimation)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}