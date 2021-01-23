package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.QuranTextDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db.QuranTextDatabase;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText;

public class QuranTextRepository {

    private QuranTextDatabase quranTextDatabase;
    private QuranTextDao quranTextDao;

    public QuranTextRepository(Application application) {
        quranTextDatabase = QuranTextDatabase.getInstance(application);
        quranTextDao = quranTextDatabase.quranTextDao();
    }

    public LiveData<QuranText> getQuranText(int id) {
        return quranTextDao.getQuranText(id);
    }

    public LiveData<List<QuranText>> getAllQuranTexts() {
        return quranTextDao.fetchAllQuranTexts();
    }
}
