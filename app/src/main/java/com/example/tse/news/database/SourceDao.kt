package com.example.tse.news.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tse.news.model.Source

@Dao
interface SourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(source: List<Source>)

    @Query("SELECT * FROM sources")
    fun getSources(): LiveData<List<Source>>

}