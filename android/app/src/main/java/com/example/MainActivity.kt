package com.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {

    companion object {
        private val counter = AtomicInteger(0)
    }

    private lateinit var binding: ActivityMainBinding

    private val engineId = counter.getAndIncrement().toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FlutterEngineManager.create(this, engineId)

        val flutter = FlutterFragment.withCachedEngine(engineId)
            .renderMode(RenderMode.texture)
            .build<FlutterFragment>()

        supportFragmentManager.beginTransaction()
            .replace(binding.flutterFrame.id, flutter, flutter::class.simpleName)
            .commit()

        binding.goBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FlutterEngineManager.destroy(engineId)
    }

}