package com.example.tse.news.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.tse.news.database.SourceUserDao
import java.util.concurrent.Executor

class SourcesUserLocalCache(private val sourceUserDao: SourceUserDao, private val ioExcecutor: Executor) {

    val TAG: String = SourcesUserLocalCache::class.java.simpleName

    fun insert(sourceUser: List<SourceUser>, insertFinished: () -> Unit){
        ioExcecutor.execute{
            Log.e(TAG, "Insert ${sourceUser.size} Sources User")
            sourceUserDao.insert(sourceUser)
            insertFinished()
        }
    }


    fun sourcesUser(): LiveData<List<SourceUser>>{
        return sourceUserDao.getSourcesUser()
    }

}