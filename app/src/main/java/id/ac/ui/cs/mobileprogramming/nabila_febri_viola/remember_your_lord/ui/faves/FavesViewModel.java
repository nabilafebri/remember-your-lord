package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.ui.faves;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is faves fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}