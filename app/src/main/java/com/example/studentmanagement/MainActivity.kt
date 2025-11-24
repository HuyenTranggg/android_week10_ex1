package com.example.studentmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etMssv: EditText
    private lateinit var etHoTen: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()
    private var selectedStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etMssv = findViewById(R.id.etMssv)
        etHoTen = findViewById(R.id.etHoTen)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recyclerView)

        setupRecyclerView()

        btnAdd.setOnClickListener {
            addStudent()
        }

        btnUpdate.setOnClickListener {
            updateStudent()
        }
    }

    private fun setupRecyclerView() {
        studentAdapter = StudentAdapter(studentList,
            onItemClick = { student ->
                etMssv.setText(student.mssv)
                etHoTen.setText(student.hoTen)
                selectedStudent = student
            },
            onDeleteClick = { student ->
                deleteStudent(student)
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter
    }

    private fun addStudent() {
        val mssv = etMssv.text.toString()
        val hoTen = etHoTen.text.toString()

        if (mssv.isNotEmpty() && hoTen.isNotEmpty()) {
            studentList.add(Student(mssv, hoTen))
            studentAdapter.notifyItemInserted(studentList.size - 1)
            clearInputFields()
        }
    }

    private fun updateStudent() {
        selectedStudent?.let { student ->
            val newHoTen = etHoTen.text.toString()
            if (newHoTen.isNotEmpty()) {
                student.hoTen = newHoTen
                val index = studentList.indexOf(student)
                if (index != -1) {
                    studentAdapter.notifyItemChanged(index)
                }
                clearInputFields()
                selectedStudent = null
            }
        }
    }

    private fun deleteStudent(student: Student) {
        val index = studentList.indexOf(student)
        if (index != -1) {
            studentList.removeAt(index)
            studentAdapter.notifyItemRemoved(index)
        }
    }

    private fun clearInputFields() {
        etMssv.text.clear()
        etHoTen.text.clear()
    }
}