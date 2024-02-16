package com.example.tpleboncoin.ui.ajout_annonce;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AjoutAnnonceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AjoutAnnonceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Ajouter une annonce");
    }

    public LiveData<String> getText() {
        return mText;
    }
}