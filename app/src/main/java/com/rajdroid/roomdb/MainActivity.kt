package com.rajdroid.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(this,AppDatabase::class.java,"room.db")
            .allowMainThreadQueries()
            .build()
    }

    val list = arrayListOf<User>()
    val adapter = UserAdapter(list)
    val dataAdded = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        db.userDao().getAllUsers().observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
            Handler().postDelayed({
                dataAdded.value = false
            }, 4000)
        })

        msg.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })



        dataAdded.observe(this, Observer {
            btn.isEnabled = !it
        })


        btn.setOnClickListener {
            db.userDao().addUser(
                User(
                    name = nameEdt.text.toString(),
                    age=ageEdt.text.toString(),
                    isActive = true
                )
            )
        }


        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }
}
