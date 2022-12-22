package com.example.anticafe.Network;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.anticafe.MainActivity;
import com.example.anticafe.Model.Person;

import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.anticafe.ServiceLocator;

public class OAuth2 {

    public void auth(MainActivity activity) {
        Bundle bundle = new Bundle();
        bundle.putString("url", "https://oauth.vk.com/authorize?client_id=51501804&scope=email&redirect_uri=" +
                "https://oauth.vk.com/blank.html&display=mobile&response_type=token&scope=offline, email");

        Navigation.findNavController(activity.binding.mainContent).navigate(
                com.example.anticafe.R.id.action_authFragment_to_webFragment, bundle);
    }

    public WebViewClient oath2VK(MainActivity activity) {
        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().contains("https://oauth.vk.com/blank.html#")) {
                    String token = Uri.parse(request.getUrl().toString().replace("#", "?")).getQueryParameter("access_token");
                    String email = Uri.parse(request.getUrl().toString().replace("#", "?")).getQueryParameter("email");
                    Toast.makeText(activity.getApplicationContext(), "OATH2VK", Toast.LENGTH_LONG).show();

                    ServiceLocator.getInstance().getTasksRepository().findPerson(email, activity).observe(activity, (person) -> {
                        if (person == null) {
                            Person newPerson = new Person();
                            newPerson.setEmail(email);

                            ServiceLocator.getInstance().setPerson(newPerson);
                        } else {
                            ServiceLocator.getInstance().setPerson(person);
                        }
                    });
                    ServiceLocator.getInstance().getVK_API().getPersonInfo(token, activity);

                    Navigation.findNavController(activity.binding.mainContent).navigate(
                            com.example.anticafe.R.id.action_webFragment_to_authFragment);

                    return false;
                }
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };
    }
}
