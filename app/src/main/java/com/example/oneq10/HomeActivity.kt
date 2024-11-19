package com.example.oneq10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val logoutButton: ImageButton = findViewById(R.id.logout)
        val paymentArea: View = findViewById(R.id.paymentArea)
        val aggregateArea: View  = findViewById(R.id.aggregateArea)
        val settingArea: View  = findViewById(R.id.settingArea)

        logoutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        paymentArea.setOnClickListener {
            val intent = Intent(this, SearchParameterActivity::class.java)
            startActivity(intent)
            finish()
        }

        aggregateArea.setOnClickListener {
            Toast.makeText(this, "集計 clicked!", Toast.LENGTH_SHORT).show()
        }

        settingArea.setOnClickListener {
            Toast.makeText(this, "設定 clicked!", Toast.LENGTH_SHORT).show()
        }

    }


}