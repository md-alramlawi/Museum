package com.alramlawi.shared.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MuseumRepository(
    private val museumApi: MuseumApi,
    private val museumStorage: MuseumStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    private suspend fun refresh() {
        museumStorage.saveMuseums(museumApi.getData())
    }

    fun getMuseum(): Flow<List<Museum>> = museumStorage.getMuseums()

    fun getMuseumById(id: Int): Flow<Museum?> = museumStorage.getMuseumById(id)
}
