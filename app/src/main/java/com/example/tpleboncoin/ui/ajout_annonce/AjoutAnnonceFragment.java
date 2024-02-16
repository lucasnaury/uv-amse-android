package com.example.tpleboncoin.ui.ajout_annonce;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tpleboncoin.R;
import com.example.tpleboncoin.databinding.FragmentAjoutAnnonceBinding;
import com.example.tpleboncoin.models.Annonce;
import com.google.android.material.snackbar.Snackbar;

public class AjoutAnnonceFragment extends Fragment {

    private FragmentAjoutAnnonceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AjoutAnnonceViewModel ajout_annonceViewModel =
                new ViewModelProvider(this).get(AjoutAnnonceViewModel.class);

        binding = FragmentAjoutAnnonceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final EditText titreAnnonce = binding.titreAnnonce;
        final EditText adresseAnnonce = binding.adresseAnnonce;
        final EditText prixAnnonce = binding.prixAnnonce;
        final Button boutonCreation = binding.boutonAjoutAnnonce;

        boutonCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On vérifie si les informations sont correctement remplies
                if(titreAnnonce.getText().toString().equals("") || adresseAnnonce.getText().toString().equals("") || prixAnnonce.getText().toString().equals("")){

                    Snackbar.make(view, "Veuillez remplir les champs pour créer l'annonce", Snackbar.LENGTH_LONG).show();

                    return;
                }

                // On créé l'objet de la nouvelle annonce
                Annonce nouvelleAnnonce = new Annonce(titreAnnonce.getText().toString(), adresseAnnonce.getText().toString(), 0, Double.parseDouble(prixAnnonce.getText().toString()));

                // On navigue vers la page d'accueil en passant la nouvelle annonce comme paramètre
                Bundle bundle = new Bundle();
                bundle.putParcelable("nouvelleAnnonce", nouvelleAnnonce);
                Navigation.findNavController(view).navigate(R.id.navigation_home, bundle);

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}