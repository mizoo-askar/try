package com.mizoo.askar.calculator.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mizoo.askar.calculator.pojo.Parse;
import com.mizoo.askar.calculator.repository.CalRepo;

public class CalViewModel extends ViewModel {
    CalRepo repo;
    LiveData<Double> liveData;
    public CalViewModel() {
        repo = new CalRepo();
    }

    public void setLiveData(String numbers){
        liveData = repo.getResult(numbers);
    }
    public LiveData<Double> getLiveData(){
        return liveData;
    }
}
