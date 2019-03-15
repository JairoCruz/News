package com.example.tse.news.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tse.news.model.SourceUser

@Dao
interface SourceUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sourceUser: List<SourceUser>)

    @Query("SELECT * FROM SourceUser")
    fun getSourcesUser() : LiveData<List<SourceUser>>

    @Query("SELECT name FROM  SourceUser")
    fun getSourceNameUser(): List<String>

    @Delete
    fun deleteSourceUser(sourceUser: SourceUser)
}