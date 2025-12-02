package com.example.studentmanagement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    private val addStudentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val newStudent = it.data?.getParcelableExtra<Student>("newStudent")
            if (newStudent != null) {
                studentList.add(newStudent)
                studentAdapter.notifyItemInserted(studentList.size - 1)
            }
        }
    }

    private val updateStudentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val updatedStudent = it.data?.getParcelableExtra<Student>("updatedStudent")
            val originalMssv = it.data?.getStringExtra("originalMssv")
            if (updatedStudent != null && originalMssv != null) {
                val index = studentList.indexOfFirst { it.mssv == originalMssv }
                if (index != -1) {
                    studentList[index] = updatedStudent
                    studentAdapter.notifyItemChanged(index)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        addInitialData()
        setupRecyclerView()
    }

    private fun addInitialData() {
        studentList.add(Student("20200001", "Nguyễn Văn A", "0123456789", "Hà Nội"))
        studentList.add(Student("20200002", "Trần Thị B", "0987654321", "TP. Hồ Chí Minh"))
    }

    private fun setupRecyclerView() {
        studentAdapter = StudentAdapter(studentList) {
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("student", it)
            updateStudentLauncher.launch(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_student -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                addStudentLauncher.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}