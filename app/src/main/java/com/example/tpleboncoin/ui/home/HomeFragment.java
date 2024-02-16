package com.example.tpleboncoin.ui.home;

import static android.util.Log.DEBUG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpleboncoin.databinding.FragmentHomeBinding;
import com.example.tpleboncoin.models.Annonce;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // On récupère l'annonce qui vient d'être créée s'il y en a une
        if(getArguments() != null && getArguments().getParcelable("nouvelleAnnonce") != null){
            Annonce newAnnonce = getArguments().getParcelable("nouvelleAnnonce");
            Log.d("HomeFragment", newAnnonce.getTitre());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}