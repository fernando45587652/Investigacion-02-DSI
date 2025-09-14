package com.example.investigacion2dsi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class DetailActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val contactId = intent.getStringExtra("contact_id")
        if (contactId != null) {
            database =
                FirebaseDatabase.getInstance().getReference("contacts").child(contactId)
            database.addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    val contact =
                        snapshot.getValue(Contact::class.java)
                    findViewById<TextView>(R.id.detail_name).text= contact?.name

                    findViewById<TextView>(R.id.detail_phone).text = contact?.phone
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}