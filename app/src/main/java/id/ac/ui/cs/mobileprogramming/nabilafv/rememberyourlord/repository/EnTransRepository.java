package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.EnTransDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db.EnTransDatabase;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans;

public class EnTransRepository {
    private EnTransDatabase enTransDatabase;
    private EnTransDao enTransDao;

    public EnTransRepository(Application application) {
        enTransDatabase = EnTransDatabase.getInstance(application);
        enTransDao = enTransDatabase.enTransDao();
    }

    public LiveData<EnTrans> getEnTrans(int id) {
        return enTransDao.getEnTrans(id);
    }

    public LiveData<List<EnTrans>> getAllEnTrans() {
        return enTransDao.fetchAllEnTrans();
    }
}
