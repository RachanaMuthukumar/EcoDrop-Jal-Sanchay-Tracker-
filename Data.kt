package com.example.ecodrops.data


import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "rainwater")

object PreferencesKeys {
    val TANK_CAPACITY = intPreferencesKey("tank_capacity")
    val CURRENT_WATER = intPreferencesKey("current_water")
    val DAILY_SAVED = floatPreferencesKey("daily_saved")
    val RAINFALL_TODAY = floatPreferencesKey("rainfall_today")
    val TOTAL_SAVED = floatPreferencesKey("total_saved")
}

class DataStoreManager(private val context: Context) {
    suspend fun addDailySaved(liters: Float) {
        context.dataStore.edit { prefs ->
            val old = prefs[PreferencesKeys.DAILY_SAVED] ?: 0f
            prefs[PreferencesKeys.DAILY_SAVED] = old + liters
        }
    }

    suspend fun addRainfallToday(mm: Float) {
        context.dataStore.edit { prefs ->
            val old = prefs[PreferencesKeys.RAINFALL_TODAY] ?: 0f
            prefs[PreferencesKeys.RAINFALL_TODAY] = old + mm
        }
    }

    val dailySaved: Flow<Float> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.DAILY_SAVED] ?: 0f
    }

    val rainfallToday: Flow<Float> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.RAINFALL_TODAY] ?: 0f
    }

    val totalSaved: Flow<Float> = context.dataStore.data.map { prefs ->
        prefs[PreferencesKeys.TOTAL_SAVED] ?: 0f
    }

    // Update total
    suspend fun addTotalSaved(liters: Float) {
        context.dataStore.edit { prefs ->
            val old = prefs[PreferencesKeys.TOTAL_SAVED] ?: 0f
            prefs[PreferencesKeys.TOTAL_SAVED] = old + liters
        }
    }
    // Save tank capacity
    suspend fun saveTankCapacity(capacity: Int) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.TANK_CAPACITY] = capacity
        }
    }

    // Save current water level
    suspend fun saveCurrentWater(liters: Int) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.CURRENT_WATER] = liters
        }
    }

    // Read tank capacity
    val tankCapacity: Flow<Int> = context.dataStore.data
        .map { prefs -> prefs[PreferencesKeys.TANK_CAPACITY] ?: 1000 }

    // Read current water
    val currentWater: Flow<Int> = context.dataStore.data
        .map { prefs -> prefs[PreferencesKeys.CURRENT_WATER] ?: 0 }


    suspend fun clearCurrentWater() {
        context.dataStore.edit { prefs ->
            prefs.remove(PreferencesKeys.CURRENT_WATER)
        }
    }
    suspend fun clearDailySaved() {
        context.dataStore.edit { prefs ->
            prefs.remove(PreferencesKeys.DAILY_SAVED)
        }
    }

    suspend fun clearRainfallToday() {
        context.dataStore.edit { prefs ->
            prefs.remove(PreferencesKeys.RAINFALL_TODAY)
        }
    }
    suspend fun clearTotalSaved() {
        context.dataStore.edit { prefs ->
            prefs.remove(PreferencesKeys.TOTAL_SAVED)
        }
    }

}

