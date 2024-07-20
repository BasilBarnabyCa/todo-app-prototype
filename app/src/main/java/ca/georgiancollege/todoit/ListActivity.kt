package ca.georgiancollege.todoit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.todoit.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample data for Upcoming tasks
        val allTasks = arrayOf(
            Task("School", "Research Paper", "Draft the introduction and literature review for the research paper", "2023-11-20", "10:00 AM"),
            Task("Work", "Team Meeting", "Discuss project milestones and deliverables with the team", "2023-11-21", "11:00 AM"),
            Task("Personal", "Doctor's Appointment", "Annual physical check-up with Dr. Smith", "2023-11-22", "02:00 PM"),
            Task("Fitness", "Morning Run", "Complete a 5km run in the park", "2023-11-23", "07:00 AM"),
            Task("School", "Research Paper", "Draft the introduction and literature review for the research paper", "2023-11-20", "10:00 AM"),
            Task("Work", "Team Meeting", "Discuss project milestones and deliverables with the team", "2023-11-21", "11:00 AM"),
            Task("Personal", "Doctor's Appointment", "Annual physical check-up with Dr. Smith", "2023-11-22", "02:00 PM"),
            Task("Fitness", "Morning Run", "Complete a 5km run in the park", "2023-11-23", "07:00 AM"),
            Task("School", "Research Paper", "Draft the introduction and literature review for the research paper", "2023-11-20", "10:00 AM"),
            Task("Work", "Team Meeting", "Discuss project milestones and deliverables with the team", "2023-11-21", "11:00 AM"),
            Task("Personal", "Doctor's Appointment", "Annual physical check-up with Dr. Smith", "2023-11-22", "02:00 PM"),
            Task("Fitness", "Morning Run", "Complete a 5km run in the park", "2023-11-23", "07:00 AM")
        )

        // Create and set the adapter for the Upcoming tasks adapter
        val taskAdapter = TaskAdapter(allTasks)

        // Set the adapter and layout manager for the Upcoming tasks RecyclerView
        binding.tasksRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }

        binding.menuBar.homeButton.setOnClickListener {
            Log.d("MenuBar", "Home button clicked")

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.menuBar.calendarButton.setOnClickListener {
            Log.d("MenuBar", "Calendar button clicked")

            startActivity(Intent(this, CalendarActivity::class.java))
            finish()
        }

        binding.menuBar.addTaskButton.setOnClickListener {
            Log.d("MenuBar", "Add task button clicked")
        }

        binding.menuBar.userProfileButton.setOnClickListener {
            Log.d("MenuBar", "User profile button clicked")
        }
    }
}