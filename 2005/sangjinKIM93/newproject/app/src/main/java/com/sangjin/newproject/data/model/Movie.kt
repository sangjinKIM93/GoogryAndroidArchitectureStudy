package com.sangjin.newproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_table")
data class Movie(

    @PrimaryKey(autoGenerate = true)
    val movieId: Long = 0L,

    @ColumnInfo(name="title")
    val title: String,

    @ColumnInfo(name="link")
    val link: String,

    @ColumnInfo(name="image")
    val image: String,

    @ColumnInfo(name="subtitle")
    val subtitle: String,

    @ColumnInfo(name="pub_date")
    val pubDate: String,

    @ColumnInfo(name="director")
    val director: String,

    @ColumnInfo(name="actor")
    val actor: String,

    @ColumnInfo(name="user_rating")
    val userRating: String
)
