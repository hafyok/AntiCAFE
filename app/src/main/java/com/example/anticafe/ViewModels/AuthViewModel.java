package com.example.anticafe.ViewModels;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.anticafe.MainActivity;
import com.example.anticafe.Model.Person;
import com.example.anticafe.R;
import com.example.anticafe.ServiceLocator;

public class AuthViewModel extends ViewModel {
    public void auth (String token, MainActivity activity) {
        if (token != null) {
            if (ServiceLocator.getInstance().getPerson() == null) {
                ServiceLocator.getInstance().getTasksRepository().findPerson(
                        activity.getPreferences(Context.MODE_PRIVATE).getString("email", ""),
                        activity
                ).observe(activity, (person) -> {
                    if (person != null) {
                        ServiceLocator.getInstance().setPerson(person);
                        Navigation.findNavController(activity.binding.mainContent).navigate(R.id.action_authFragment_to_roomsFragment);
                    } else {
                        activity.getPreferences(Context.MODE_PRIVATE).edit().remove("email").remove("token").apply();
                    }
                });
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    public LiveData<Person> auth (String login, String password, MainActivity activity) {
        ServiceLocator.getInstance().getTasksRepository().findPerson(login, password, activity).observe(activity, (person) -> {
            if (person != null) {
                ServiceLocator.getInstance().setPerson(person);
                activity.getPreferences(Context.MODE_PRIVATE).edit().putString("email", person.getEmail());
            }
        });
        return ServiceLocator.getInstance().getTasksRepository().findPerson(login, password, activity);
    }
}
