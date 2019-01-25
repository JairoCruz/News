package com.example.tse.news.repository

import com.example.tse.news.model.SourceUser
import com.example.tse.news.model.SourceUserResult
import com.example.tse.news.model.SourcesUserLocalCache

class SourceUserRepository(private val cache: SourcesUserLocalCache) {

    private val TAG: String = SourceUserRepository::class.java.simpleName

    private var isRequestInProgress = false

    // Get List Sources User
    fun getSourcesUser(): SourceUserResult {

        val data = cache.sourcesUser()

        return SourceUserResult(data)

    }


    // Save List Sources User
    fun saveDataSourceUser(sourcesUser: List<SourceUser>){
        if(isRequestInProgress) return

        isRequestInProgress = true

        if (isRequestInProgress) {
            cache.insert(sourcesUser, { isRequestInProgress = false})
        }
    }
}