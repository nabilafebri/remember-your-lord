package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Weather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeather(weather: Weather)
}