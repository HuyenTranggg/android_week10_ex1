package com.example.studentmanagement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val etMSSV = findViewById<EditText>(R.id.etMSSV)
        val etHoTen = findViewById<EditText>(R.id.etHoTen)
        val etSDT = findViewById<EditText>(R.id.etSDT)
        val etDiaChi = findViewById<EditText>(R.id.etDiaChi)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val newStudent = Student(
                etMSSV.text.toString(),
                etHoTen.text.toString(),
                etSDT.text.toString(),
                etDiaChi.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra("newStudent", newStudent)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}