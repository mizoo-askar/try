package com.mizoo.askar.calculator.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mizoo.askar.calculator.pojo.Parse;

public class CalRepo {
    private Parse parse;

    public CalRepo() {
        this.parse = new Parse();
    }

    public LiveData<Double> getResult(String numbers){
        final MutableLiveData<Double> liveData = new MutableLiveData<>();
        liveData.setValue(parse.parse(numbers));
        return liveData;
    }


}
