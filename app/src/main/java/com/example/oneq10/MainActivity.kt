package com.example.oneq10

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.oneq10.databinding.ActivityMainBinding
import com.example.oneq10.db.AppDatabase
import com.example.oneq10.db.DatabaseProvider
import com.example.oneq10.db.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var loginButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener(View.OnClickListener {

            val usernameInput = binding.username.text.toString()
            val passwordInput = binding.password.text.toString()
            val database = DatabaseProvider.getDatabase(this)
            val userDao = database.userDao()

            GlobalScope.launch(Dispatchers.IO) {
                val currentUser = userDao.getUser(usernameInput)
                withContext(Dispatchers.Main) {
                    if (currentUser != null &&
                        usernameInput == currentUser.userId &&
                        passwordInput == currentUser.password) {
                        val intent = Intent(this@MainActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Login Failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

//            val database = DatabaseProvider.getDatabase(this)
//            val userDao = database.userDao()
//            val currentUser : UserEntity= userDao.getUser(binding.username.text.toString())
//            if (currentUser != null &&
//                username.text.toString().isNotEmpty()&&
//                binding.username.text.toString().equals(currentUser.userId) &&
//                binding.password.text.toString().isNotEmpty()&&
//                binding.password.text.toString().equals( currentUser.password)){
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
//            }
        })

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}