package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.dao.QuranTextDao;
import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.db.QuranTextDatabase;
import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.model.QuranText;

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

    public LiveData<List<QuranText>> getQuranTexts() {
        return quranTextDao.fetchAllQuranTexts();
    }
}
