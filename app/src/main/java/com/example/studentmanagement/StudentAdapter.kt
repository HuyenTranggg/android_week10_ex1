package com.example.studentmanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private var studentList: MutableList<Student>,
    private val onItemClick: (Student) -> Unit,
    private val onDeleteClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHoTen: TextView = itemView.findViewById(R.id.tvHoTen)
        val tvMssv: TextView = itemView.findViewById(R.id.tvMssv)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)

        fun bind(student: Student) {
            tvHoTen.text = student.hoTen
            tvMssv.text = student.mssv
            itemView.setOnClickListener { onItemClick(student) }
            ivDelete.setOnClickListener { onDeleteClick(student) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position])
    }

    override fun getItemCount() = studentList.size

    fun updateData(newList: List<Student>) {
        studentList.clear()
        studentList.addAll(newList)
        notifyDataSetChanged()
    }
}