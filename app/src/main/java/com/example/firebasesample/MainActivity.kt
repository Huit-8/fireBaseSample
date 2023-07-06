package com.example.firebasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasesample.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firestoreをインスタンス化
        val db = Firebase.firestore

        // RecyclerViewの設定
        val taskAdapter = TaskAdapter()
        binding.recyclerView.adapter = taskAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        // アプリ起動時に、保存されているデータを取得する
        db.collection("tasks")
            .get()
            .addOnSuccessListener { tasks ->
                val taskList = ArrayList<Task>()
                tasks.forEach { taskList.add(it.toObject(Task::class.java)) }
//                taskAdapter.submitList(taskList)
                taskAdapter.submitList(taskList)
                println("addedおおおおおおお")
            }
            .addOnFailureListener { exception ->
                Log.d("tag", "Error getting documents: ", exception)
            }

        // データの変更をリアルタイムでアプリに反映する
        val docRef = db.collection("tasks")
        docRef.addSnapshotListener { tasks, e ->
            if (e != null) {
                Log.w("tag", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (tasks != null) {
                val taskList = ArrayList<Task>()
                tasks.forEach { taskList.add(it.toObject(Task::class.java)) }
                taskAdapter.submitList(taskList)
            } else {
                Log.d("tag", "Current data: null")
            }
        }

        binding.addButton.setOnClickListener {

            // Taskをインスタンス化
            val task = Task(
                title = binding.titleEditText.text.toString(),
            )

            db.collection("tasks")
                .add(task)
                .addOnSuccessListener { documentReference ->
                    Log.d("tag", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("tag", "Error adding document", e)
                }

        }






//        val tasks = listOf<Task>(
//            Task("Freesia","遊び"),
//            Task("Lily","勉強"),
//            Task("SunFlower","テスト"),
//        )


    }
}