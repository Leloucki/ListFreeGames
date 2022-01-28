package com.example.listfreegames.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
{"id":1,
"title":"Dauntless",
"thumbnail":"https:\/\/www.freetogame.com\/g\/1\/thumbnail.jpg",
"short_description":"A free-to-play, co-op action RPG with gameplay similar to Monster Hunter.",
"game_url":"https:\/\/www.freetogame.com\/open\/dauntless",
"genre":"MMORPG",
"platform":"PC (Windows)",
"publisher":"Phoenix Labs","developer":"Phoenix Labs, Iron Galaxy",
"release_date":"2019-05-21",
"freetogame_profile_url":"https:\/\/www.freetogame.com\/dauntless"}
 */

@Entity(tableName = "games")
data class Game (
    @SerializedName("id")
    @PrimaryKey val gameId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("short_description")
    val description: String,

    @SerializedName("game_url")
    val game_url: String,

    @SerializedName("genre")
    val genre: String,

    @SerializedName("platform")
    val platform: String,

    @SerializedName("publisher")
    val publisher: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("freetogame_profile_url")
    val freetogame_profile_url: String
)