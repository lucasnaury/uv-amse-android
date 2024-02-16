package com.example.tpleboncoin.ui.ajout_annonce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpleboncoin.databinding.FragmentAjoutAnnonceBinding;

public class AjoutAnnonceFragment extends Fragment {

    private FragmentAjoutAnnonceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AjoutAnnonceViewModel ajout_annonceViewModel =
                new ViewModelProvider(this).get(AjoutAnnonceViewModel.class);

        binding = FragmentAjoutAnnonceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAjoutAnnonce;
        ajout_annonceViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}