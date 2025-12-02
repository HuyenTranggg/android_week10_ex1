package com.example.studentmanagement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class StudentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        val etMSSV = findViewById<EditText>(R.id.etMSSV)
        val etHoTen = findViewById<EditText>(R.id.etHoTen)
        val etSDT = findViewById<EditText>(R.id.etSDT)
        val etDiaChi = findViewById<EditText>(R.id.etDiaChi)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        val student = intent.getParcelableExtra<Student>("student")

        if (student != null) {
            etMSSV.setText(student.mssv)
            etHoTen.setText(student.hoTen)
            etSDT.setText(student.sdt)
            etDiaChi.setText(student.diaChi)
        }

        btnUpdate.setOnClickListener {
            val updatedStudent = Student(
                etMSSV.text.toString(),
                etHoTen.text.toString(),
                etSDT.text.toString(),
                etDiaChi.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra("updatedStudent", updatedStudent)
            if (student != null) {
                resultIntent.putExtra("originalMssv", student.mssv)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}