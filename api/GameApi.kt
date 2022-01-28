package com.example.listfreegames.api

import com.example.listfreegames.data.Game
import retrofit2.http.GET

interface GameApi {

    companion object {
        const val BASE_URL = "https://www.freetogame.com/api/"
    }

    @GET("games?sort-by=relevance")
    suspend fun getGames(): List<Game>
}