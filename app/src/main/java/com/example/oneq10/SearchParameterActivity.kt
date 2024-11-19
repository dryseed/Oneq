package com.example.oneq10

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class SearchParameterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_parameter)

        val beginDateButton: Button = findViewById(R.id.begin_date_button)
        val beginTimeButton: Button = findViewById(R.id.begin_time_button)
        val endTimeButton: Button = findViewById(R.id.end_time_button)
        var searchBackHome: ImageView = findViewById(R.id.search_back_to_home)

        // 获取当前日期并显示在按钮上
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        beginDateButton.text = dateFormat.format(calendar.time)
        beginTimeButton.text = timeFormat.format(calendar.time)
        endTimeButton.text = timeFormat.format(calendar.time)

        // 设置按钮点击事件，显示日期选择器
        beginDateButton.setOnClickListener {
            // 初始化日历为当前日期
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // 创建日期选择对话框
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // 更新按钮上的日期
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    beginDateButton.text = dateFormat.format(calendar.time)
                },
                year, month, day
            )
            // 显示对话框
            datePickerDialog.show()
        }

        // 设置起始时间按钮点击事件
        beginTimeButton.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // 显示时间选择对话框
            TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    // 更新按钮上的时间
                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                    calendar.set(Calendar.MINUTE, selectedMinute)
                    beginTimeButton.text = timeFormat.format(calendar.time)
                },
                hour, minute, true
            ).show()
        }

        // 设置结束时间按钮点击事件
        endTimeButton.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // 显示时间选择对话框
            TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    // 更新按钮上的时间
                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                    calendar.set(Calendar.MINUTE, selectedMinute)
                    endTimeButton.text = timeFormat.format(calendar.time)
                },
                hour, minute, true
            ).show()
        }

        searchBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.searchParameter)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}