package com.example.anticafe.View;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anticafe.MainActivity;
import com.example.anticafe.R;
import com.example.anticafe.ServiceLocator;
import com.example.anticafe.ViewModels.AuthViewModel;
import com.example.anticafe.databinding.FragmentAuthBinding;


public class AuthFragment extends Fragment {
    private FragmentAuthBinding mBinding;
    private AuthViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        Log.d("Auth", "1");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Auth", "2");
        mBinding = FragmentAuthBinding.inflate(inflater, container, false);

        mBinding.vkAuth.setOnClickListener((view) -> ServiceLocator.getInstance().getVK_API().auth.auth((MainActivity) requireActivity()));

        mBinding.auth.setOnClickListener((view) -> {
            if (!mBinding.login.getText().toString().isEmpty() && !mBinding.password.getText().toString().isEmpty()) {
                mViewModel.auth(mBinding.login.getText().toString(), mBinding.password.getText().toString(), (MainActivity) requireActivity()).observe(getViewLifecycleOwner(), (person) -> {
                    if (person != null) {
                        Navigation.findNavController(((MainActivity) requireActivity()).binding.mainContent).navigate(R.id.action_authFragment_to_roomsFragment);
                    }
                });
            }
        });

        Log.d("Auth", "3");
        try {
            if (getActivity().getPreferences(Context.MODE_PRIVATE).contains("token")) {
                mViewModel.auth(
                        getActivity().getPreferences(Context.MODE_PRIVATE).getString("token", null),
                        (MainActivity) requireActivity()

                );
                Log.d("Auth", "4");
            }
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            Log.d("Auth", "5");
        }

        Log.d("Auth", "6");
        return mBinding.getRoot();
    }

}