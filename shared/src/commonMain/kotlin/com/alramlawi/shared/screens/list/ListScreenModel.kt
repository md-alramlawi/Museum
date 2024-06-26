package com.alramlawi.shared.screens.list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.alramlawi.shared.data.Museum
import com.alramlawi.shared.data.MuseumRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListScreenModel(museumRepository: MuseumRepository) : ScreenModel {
    val objects: StateFlow<List<Museum>> =
        museumRepository.getMuseum()
            .stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
