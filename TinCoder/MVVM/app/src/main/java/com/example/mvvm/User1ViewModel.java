package com.example.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class User1ViewModel extends ViewModel {
    private MutableLiveData<List<User1>> mListUser1LiveData;
    private List<User1> mlistUser1;

    public User1ViewModel() {
        mListUser1LiveData = new MutableLiveData<>();
        initData();
    }

    private void initData() {
        mlistUser1 = new ArrayList<>();
        mlistUser1.add(new User1(R.drawable.img_avatar2,"tri@gmail.com","hue"));

        mListUser1LiveData.setValue(mlistUser1);
    }

    public void addUser(User1 user1){
        mlistUser1.add(user1);

        mListUser1LiveData.setValue(mlistUser1);
    }

    public MutableLiveData<List<User1>> getListUser1LiveData() {
        return mListUser1LiveData;
    }
}
