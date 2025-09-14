package com.example.investigacion2dsi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*

class AddContactActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var nameInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        nameInput = findViewById(R.id.input_name)
        phoneInput = findViewById(R.id.input_phone)
        saveButton = findViewById(R.id.save_contact_button)
        database =
            FirebaseDatabase.getInstance().getReference("contacts")

        saveButton.setOnClickListener {
            val id = UUID.randomUUID().toString()
            val name = nameInput.text.toString()
            val phone = phoneInput.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val contact = Contact(id, name, phone)
                database.child(id).setValue(contact).addOnSuccessListener {
                    Toast.makeText(this, "Contacto agregado",
                        Toast.LENGTH_SHORT).show()
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "Error al guardar",
                        Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Completa todos los campos",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}