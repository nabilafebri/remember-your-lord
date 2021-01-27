package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.ActivityRepository;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.ActivityRepositoryInterface;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.WeatherRepository;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.WeatherRepositoryInterface;

@Module
@InstallIn(ApplicationComponent.class)
interface RememberYourLordRepository {

    @Binds
    ActivityRepositoryInterface bindActivityRepository(ActivityRepository activityRepository);

    @Binds
    WeatherRepositoryInterface bindWeatherRepository(WeatherRepository weatherRepository);
}
