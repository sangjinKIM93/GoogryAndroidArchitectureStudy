package com.sangjin.newproject.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sangjin.newproject.data.model.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Insert
    fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movie_table")
    fun getLocalMoviesData(): Flowable<List<Movie>>

    @Query("DELETE FROM movie_table")
    fun deleteAll()

}