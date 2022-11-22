package com.example.anticafe.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.anticafe.Game;
import com.example.anticafe.GameRepository;

import java.util.List;

public class PeopleViewModel extends ViewModel {
    private GameRepository mRepository;

    public LiveData<List<Game>> getAllGames(){
        return GameRepository.getInstance().getAllGames();
    }
}
