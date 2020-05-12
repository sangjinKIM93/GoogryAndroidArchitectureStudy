package com.sangjin.newproject.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sangjin.newproject.data.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert
    fun insert(movie: Movie)

    @Query("SELECT * FROM movie_table")
    fun getLocalMoviesData(): Single<List<Movie>>

    @Query("DELETE FROM movie_table")
    fun deleteAll()

}