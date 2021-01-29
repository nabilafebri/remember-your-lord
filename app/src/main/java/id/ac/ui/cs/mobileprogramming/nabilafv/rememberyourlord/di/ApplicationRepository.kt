package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.*


@Module
@InstallIn(SingletonComponent::class)
interface ApplicationRepository {
    @Binds
    fun bindActivityRepository(activityRepository: ActivityRepository): ActivityRepositoryInterface

    @Binds
    fun bindEnTransRepository(enTransRepository: EnTransRepository): EnTransRepositoryInterface

    @Binds
    fun bindIdTransRepository(idTransRepository: IdTransRepository): IdTransRepositoryInterface

    @Binds
    fun bindQuranTextRepository(quranTextRepository: QuranTextRepository): QuranTextRepositoryInterface

    @Binds
    fun bindWeatherRepository(weatherRepository: WeatherRepository): WeatherRepositoryInterface
}