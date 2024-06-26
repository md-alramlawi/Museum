package com.alramlawi.shared.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface MuseumStorage {
    suspend fun saveMuseums(newList: List<Museum>)

    fun getMuseumById(id: Int): Flow<Museum?>

    fun getMuseums(): Flow<List<Museum>>
}

class InMemoryMuseumStorage : MuseumStorage {
    private val storedList = MutableStateFlow(emptyList<Museum>())

    override suspend fun saveMuseums(newList: List<Museum>) {
        storedList.value = newList
    }

    override fun getMuseumById(id: Int): Flow<Museum?> {
        return storedList.map { objects ->
            objects.find { it.objectID == id }
        }
    }

    override fun getMuseums(): Flow<List<Museum>> = storedList
}
