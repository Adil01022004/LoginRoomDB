package com.example.roomdb

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.roomdb.databinding.ActivityMainBinding
import com.example.roomdb.ui.theme.RoomDBTheme

class MainActivity : ComponentActivity() {

    private lateinit var  user_email: EditText
    private lateinit var user_password: EditText
    private lateinit var login_button: TextView
    private lateinit var report: TextView
    private lateinit var binding: ActivityMainBinding
    private lateinit var startRegister: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        login_button = findViewById(R.id.login_button)
        user_password = findViewById(R.id.user_password)
        report = findViewById(R.id.report)
        user_email = findViewById(R.id.user_email)
        startRegister = findViewById(R.id.startRegister)


        val db = MainDB.getDB(this)




        login_button.setOnClickListener {
            val item = Item(null,
                user_email.text.toString(),
                user_password.text.toString())
            Thread{
                var checkInDb = db.getDao().checkUser(user_email.text.toString(), user_password.text.toString())
                runOnUiThread {
                    if (checkInDb == 0) {
                        report.text = "User e-mail or password incorrect!"
                    } else {
                        report.text = "Login successful"
                    }
                }

            }.start()


        }

        startRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

