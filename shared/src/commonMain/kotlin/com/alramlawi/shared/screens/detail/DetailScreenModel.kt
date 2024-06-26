package com.alramlawi.shared.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import com.alramlawi.shared.data.Museum
import com.alramlawi.shared.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

class DetailScreenModel(private val museumRepository: MuseumRepository) : ScreenModel {
    fun getObject(id: Int): Flow<Museum?> =
        museumRepository.getMuseumById(id)
}
