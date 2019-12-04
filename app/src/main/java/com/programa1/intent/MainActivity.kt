package com.programa1.intent

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.view.View
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.longToast
import java.util.jar.Manifest


const val SUMA_REQUEST = 1
const val REQUEST_IMAGE_CAPTURE = 0

class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        btnCamara.setOnClickListener { camera(it) }
        btnAmarillo.setOnClickListener {
            val miintent = Intent(this, DisplayMessageActivity::class.java)

            miintent.putExtra("color", "Amarillo")

            startActivity(miintent)
        }

        btnRojo.setOnClickListener {
            val miintent = Intent(this, DisplayMessageActivity::class.java)

            miintent.putExtra("color", "Rojo")

            startActivity(miintent)
        }

        btnBuscar.setOnClickListener {

            browse("https://www.google.es")


        }

        btnSumar.setOnClickListener {
            val miintent = Intent(this, SumarActivity::class.java)
            miintent.putExtra("a", Numero1.text)
            miintent.putExtra("b", Numero2.text)
            startActivityForResult(miintent, SUMA_REQUEST)
        }


    }

    /* TODO RECOGER VALOR */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SUMA_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    textResultado.setText(data.getIntExtra("suma", 0).toString())
                }

            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            imageView2.setImageBitmap(imageBitmap)
        }

    }

    private fun camera(y: View?) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permisos no concedido
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.CAMERA
                )
            ) {
                longToast("Permitido")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    1
                )
            } else {
                longToast("Rechazado permanentemente")

            }
        } else {
            longToast("Ya permitido")
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }
}

