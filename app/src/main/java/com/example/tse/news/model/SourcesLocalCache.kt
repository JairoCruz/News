package com.example.tse.news.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.tse.news.database.SourceDao
import java.util.concurrent.Executor

class SourcesLocalCache(private val sourceDao: SourceDao, private val ioExcecutor: Executor){
    val TAG: String = SourcesLocalCache::class.java.simpleName

    fun insert(sources: List<Source>, insertFinished: () -> Unit){
        ioExcecutor.execute{
            Log.e(TAG, "Insert ${sources.size} sources")
            sourceDao.insert(sources)
            insertFinished()
        }
    }

    fun sourcesNews(): LiveData<List<Source>>{
        return sourceDao.getSources()
    }
}