package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.di;

import android.content.Context;

import androidx.room.Room;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.BuildConfig;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api.ApiHelper;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api.ApiHelperInterface;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.api.ApiService;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.ActivityDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.QuoteDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.WeatherDao;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class ApplicationModule {

    @Provides
    public String provideWeatherBaseUrl() {
        return "https://api.openweathermap.org/data/2.5/";
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        } else {
            return new OkHttpClient.Builder().build();
        }
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, String BASE_URL) {
        return new Retrofit.Builder()
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder()
                                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                        .setPrettyPrinting()
                                        .create()
                        )
                )
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    public ApiHelperInterface provideApiHelper(ApiHelper apiHelper) {
        return apiHelper;
    }

    @Singleton
    @Provides
    public RememberYourLordDatabase provideDatabase(@ApplicationContext Context appContext) {
        return Room.databaseBuilder(
                appContext,
                RememberYourLordDatabase.class,
                "ryl-db")
                .build();
    }

    @Singleton
    @Provides
    public ActivityDao provideActivityDao(RememberYourLordDatabase db) {
        return db.activityDao();
    }

    @Singleton
    @Provides
    public WeatherDao provideWeatherDao(RememberYourLordDatabase db) {
        return db.weatherDao();
    }

    @Singleton
    @Provides
    public QuoteDao provideQuoteDao(RememberYourLordDatabase db) {
        return db.quoteDao();
    }
}
