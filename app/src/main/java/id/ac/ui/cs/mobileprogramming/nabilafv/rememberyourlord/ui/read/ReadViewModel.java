package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.read;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReadViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is read fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}