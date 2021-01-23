package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.read;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.QuranTextRepository;

public class QuranTextViewModel extends AndroidViewModel {
    private QuranTextRepository quranTextRepository;

    public QuranTextViewModel(Application application) {
        super(application);
        quranTextRepository = new QuranTextRepository(application);
    }

    public LiveData<QuranText> getQuranText(int id) {
        return quranTextRepository.getQuranText(id);
    }

    public LiveData<List<QuranText>> getQuranTexts() {
        return quranTextRepository.getAllQuranTexts();
    }
}
