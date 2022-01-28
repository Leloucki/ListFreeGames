package com.example.listfreegames.data

import androidx.room.withTransaction
import com.example.listfreegames.api.GameApi
import com.example.listfreegames.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val api: GameApi,
    private val db: GameDatabase
) {
    private val gameDao = db.gameDao()

    fun getGames() = networkBoundResource(
        query = {
            gameDao.getAllGames()
        },
        fetch = {
            delay(2000)
            api.getGames()
        },
        saveFetchResult = { games ->
            db.withTransaction {
                gameDao.deleteAllGames()
                gameDao.insertGames(games)
            }
        }
    )

    fun searchDataBase(searchQuery: String) = networkBoundResource(
        query = {
            gameDao.searchDataBase(searchQuery)
        },
        fetch = {
            delay(2000)
            gameDao.searchDataBase(searchQuery)
        },
        saveFetchResult = { games ->
            db.withTransaction {

            }
        }
    )
}