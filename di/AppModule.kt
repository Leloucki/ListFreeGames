package com.example.listfreegames.di
//dependencia de injeção
import android.app.Application
import androidx.room.Room
import com.example.listfreegames.api.GameApi
import com.example.listfreegames.data.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(GameApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGamesApi(retrofit: Retrofit): GameApi =
        retrofit.create(GameApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : GameDatabase =
        Room.databaseBuilder(app, GameDatabase::class.java, "games")
            .build()
}