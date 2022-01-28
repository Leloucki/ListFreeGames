package com.example.listfreegames.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<Game>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(game: List<Game>)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    @Query("SELECT * FROM games WHERE title LIKE :searchQuery OR genre LIKE :searchQuery")
    fun searchDataBase(searchQuery: String): Flow<List<Game>>
}