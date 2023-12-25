package com.example.trackgoals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.trackgoals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()

        // Inflate layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi elemen-elemen tampilan menggunakan binding
        val username = binding.Username
        val password = binding.Password
        val loginButton = binding.LoginButton

        loginButton.setOnClickListener {
            if (username.text.toString() == "user" && password.text.toString() == "1234") {
                // Jika kredensial valid, pindah ke halaman baru (contoh: HomeActivity)
                val intent = Intent(this, BarActivity::class.java)
                startActivity(intent)
                finish() // Tutup aktivitas login agar tidak dapat kembali ke sini
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Gagal!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}