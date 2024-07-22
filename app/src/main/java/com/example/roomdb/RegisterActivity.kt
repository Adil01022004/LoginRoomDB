package com.example.roomdb

import android.content.Intent
import android.os.Bundle
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

class RegisterActivity : ComponentActivity() {


    private lateinit var  user_email: EditText
    private lateinit var user_password: EditText
    private lateinit var register_button: TextView

    private lateinit var startLogin: TextView
    private lateinit var report: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)


        user_password = findViewById(R.id.user_password)
        user_email = findViewById(R.id.user_email)
        register_button = findViewById(R.id.register_button)
        startLogin = findViewById(R.id.startLogin)
        report = findViewById(R.id.report_text)

        val db = MainDB.getDB(this)
        register_button.setOnClickListener {
            val item = Item(null,
                user_email.text.toString(),
                user_password.text.toString())



//            Thread{
//                db.getDao().insertItem(item)
//            }.start()

            Thread{
                var checkInDb = db.getDao().checkUser(user_email.text.toString(), user_password.text.toString())
                runOnUiThread {
                    if (checkInDb == 0) {

                        Thread{
                            db.getDao().insertItem(item)
                        }.start()
                        report.text = "Successfully registered"
                    } else {
                        report.text = "You already have an Account"
                    }
                }

            }.start()
        }

        startLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

