package com.example.ecodrops.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecodrops.data.DataStoreManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SharedViewModel(context: Context) : ViewModel() {
    private val dataStore = DataStoreManager(context)

    val tankCapacity = dataStore.tankCapacity.stateIn(
        viewModelScope, SharingStarted.Eagerly, 1000
    )

    val currentWater = dataStore.currentWater.stateIn(
        viewModelScope, SharingStarted.Eagerly, 0
    )

    val progress: StateFlow<Float> = combine(currentWater, tankCapacity) { water, capacity ->
        if (capacity > 0) water.toFloat() / capacity else 0f
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 0f)

    // Calculate litersSaved from rainfall input
    private fun calculateLitersSaved(
        area: Float,
        rainfall: Float,
        runoffCoefficient: Float = 0.85f
    ): Float {
        return area * rainfall * runoffCoefficient
    }


    fun addRain(area: Float, tank: Float, rainfall: Float, runoffCoefficient: Float = 0.85f) {
        val litersSaved = calculateLitersSaved(area, rainfall, runoffCoefficient)

        viewModelScope.launch {
            dataStore.saveTankCapacity(tank.toInt())
            val newWater = currentWater.value + litersSaved.toInt()
            dataStore.saveCurrentWater(newWater)

            dataStore.addDailySaved(litersSaved)
            dataStore.addRainfallToday(rainfall)

            dataStore.addTotalSaved(litersSaved)
        }
    }

    val dailySaved = dataStore.dailySaved.stateIn(
        viewModelScope, SharingStarted.Eagerly, 0f
    )

    val rainfallToday = dataStore.rainfallToday.stateIn(
        viewModelScope, SharingStarted.Eagerly, 0f
    )

    val totalSaved = dataStore.totalSaved.stateIn(
        viewModelScope, SharingStarted.Eagerly, 0f
    )

    // Optional: water days metric
    fun getWaterDays(): Float {
        val totalLiters = currentWater.value.toFloat()
        return totalLiters / 135f  // average daily consumption per person
    }

    fun resetCurrentWater() {
        viewModelScope.launch {
            dataStore.clearCurrentWater()
            dataStore.clearDailySaved()
            dataStore.clearRainfallToday()

        }
    }
    fun reset(){
        viewModelScope.launch {
            dataStore.clearCurrentWater()
            dataStore.clearTotalSaved()
            dataStore.clearDailySaved()
            dataStore.clearRainfallToday()
        }
    }


}
