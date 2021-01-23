package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.IdTransDao;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.db.IdTransDatabase;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.IdTrans;

public class IdTransRepository {
    private IdTransDatabase idTransDatabase;
    private IdTransDao idTransDao;

    public IdTransRepository(Application application) {
        idTransDatabase = IdTransDatabase.getInstance(application);
        idTransDao = idTransDatabase.idTransDao();
    }

    public LiveData<IdTrans> getIdTrans(int id) {
        return idTransDao.getIdTrans(id);
    }

    public LiveData<List<IdTrans>> getAllIdTrans() {
        return idTransDao.fetchAllIdTrans();
    }
}
