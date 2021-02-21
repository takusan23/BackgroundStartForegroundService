package io.github.takusan23.backgroundstartforegroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startForegroundService(Intent(this@MainActivity, ExampleService::class.java))
    }

    /** バッググラウンド判定 */
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
        Timer().schedule(5000) {
            runOnUiThread {
                Toast.makeText(this@MainActivity, "Start service", Toast.LENGTH_SHORT).show()
            }
        }
    }
}