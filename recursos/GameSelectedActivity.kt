package com.example.listfreegames.recursos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.listfreegames.databinding.SelectedItemBinding

class GameSelectedActivity : AppCompatActivity() {

    private lateinit var binding: SelectedItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectedItemBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

    }
}