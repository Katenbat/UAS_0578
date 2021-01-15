package com.example.uaspcs_18120578.value.favorit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> testing;

    public FavoritViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
        testing = new MutableLiveData<>();
        testing.setValue("tes");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> tes() { return testing;}
}