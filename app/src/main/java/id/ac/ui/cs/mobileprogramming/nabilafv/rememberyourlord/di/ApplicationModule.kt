package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.di

import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.BuildConfig
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api.ApiHelper
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api.ApiHelperInterface
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api.ApiService
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.*
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db.EnTransDatabase
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db.IdTransDatabase
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db.QuranTextDatabase
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db.RememberYourLordDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideWeatherBaseUrl(): String {
        return "https://api.openweathermap.org/data/2.5/"
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        } else {
            OkHttpClient.Builder().build()
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .setPrettyPrinting()
                        .create()
                )
            )
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: ApiHelper): ApiHelperInterface {
        return apiHelper
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): RememberYourLordDatabase {
        return Room.databaseBuilder(
            appContext,
            RememberYourLordDatabase::class.java,
            "ryl-db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideEnTransDatabase(@ApplicationContext appContext: Context): EnTransDatabase {
        return Room.databaseBuilder(
            appContext,
            EnTransDatabase::class.java,
            "en-trans-db"
        )
            .createFromAsset("database/en.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideIdTransDatabase(@ApplicationContext appContext: Context): IdTransDatabase {
        return Room.databaseBuilder(
            appContext,
            IdTransDatabase::class.java,
            "id-trans-db"
        )
            .createFromAsset("database/id.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideQuranTextDatabase(@ApplicationContext appContext: Context): QuranTextDatabase {
        return Room.databaseBuilder(
            appContext,
            QuranTextDatabase::class.java,
            "quran-text-db"
        )
            .createFromAsset("database/arabic.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideActivityDao(db: RememberYourLordDatabase): ActivityDao {
        return db.activityDao()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(db: RememberYourLordDatabase): WeatherDao {
        return db.weatherDao()
    }

    @Singleton
    @Provides
    fun provideEnTransDao(db: EnTransDatabase): EnTransDao {
        return db.enTransDao()
    }

    @Singleton
    @Provides
    fun provideIdTransDao(db: IdTransDatabase): IdTransDao {
        return db.idTransDao()
    }

    @Singleton
    @Provides
    fun provideQuranTextDao(db: QuranTextDatabase): QuranTextDao {
        return db.quranTextDao()
    }
}