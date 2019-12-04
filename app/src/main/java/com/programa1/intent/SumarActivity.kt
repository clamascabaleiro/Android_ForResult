package com.programa1.intent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sumar.*
import android.content.Intent

class SumarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumar)

        val a = intent.getIntExtra("a", 0)
        val b = intent.getIntExtra("b", 0)

        btnVolver.setOnClickListener {
            val suma = a + b
            val data = Intent()
            data.putExtra("suma",suma)
            setResult(Activity.RESULT_OK,data)
            finish()
        }
    }
}
