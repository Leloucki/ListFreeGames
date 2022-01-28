package com.example.listfreegames.recursos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listfreegames.R
import com.example.listfreegames.databinding.ActivityGameBinding
import com.example.listfreegames.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity(){

    private val viewModel: GameViewModel by viewModels()
    private val gameAdapter = GameAdapter(GameAdapter.OnClickListener { photo ->
        Toast.makeText(applicationContext, "${photo.title}", Toast.LENGTH_SHORT).show() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            recyclerView.apply {
                adapter = gameAdapter
                layoutManager = LinearLayoutManager(this@GameActivity)
            }

            viewModel.games.observe(this@GameActivity) { result ->
                gameAdapter.submitList(result.data)
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = getString(R.string.errorAPI)
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i(localClassName, "dentro de TextSubmit")
                if(query != null){
                    searchDataBase(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.i(localClassName, "dentro de TextChange")
                if(query != null){
                    searchDataBase(query)
                }
                return true
            }

        })
        Log.i(localClassName, "dentro de onCrateOptionsMenu")
        return true
    }

    private fun searchDataBase(query: String?){
        val searchQuery = "%$query%"
        val binding = ActivityGameBinding.inflate(layoutInflater)
        Log.i(localClassName, "dentro de searchDataBase")

        binding.apply {
            recyclerView.apply {
                adapter = gameAdapter
                layoutManager = LinearLayoutManager(this@GameActivity)
            }

            viewModel.searchDataBase(searchQuery).observe(this@GameActivity) { result ->
                gameAdapter.submitList(result.data)
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = getString(R.string.errorSearch)
            }
        }
    }


}