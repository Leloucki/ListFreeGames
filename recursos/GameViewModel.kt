package com.example.listfreegames.recursos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.listfreegames.data.Game
import com.example.listfreegames.data.GameRepository
import com.example.listfreegames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    val games = repository.getGames().asLiveData()

    fun searchDataBase(searchQuery: String): LiveData<Resource<List<Game>>> {
        return repository.searchDataBase(searchQuery).asLiveData()
    }
}