package com.example.tpleboncoin.ui.ajout_annonce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tpleboncoin.R;
import com.example.tpleboncoin.databinding.FragmentAjoutAnnonceBinding;
import com.example.tpleboncoin.db.DBManager;
import com.example.tpleboncoin.models.Annonce;
import com.google.android.material.snackbar.Snackbar;

public class AjoutAnnonceFragment extends Fragment {

    private FragmentAjoutAnnonceBinding binding;

    private ActivityResultLauncher<Intent> activityResultLauncher;


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

        // Initialisation de la DB
        DBManager dbManager = DBManager.getDBManager(this.getContext());
        dbManager.open();


        boutonCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On vérifie si les informations sont correctement remplies
                if(titreAnnonce.getText().toString().equals("") || adresseAnnonce.getText().toString().equals("") || prixAnnonce.getText().toString().equals("")){

                    Snackbar.make(view, "Veuillez remplir les champs pour créer l'annonce", Snackbar.LENGTH_LONG).show();

                    return;
                }

                // On créé l'objet de la nouvelle annonce
                Annonce nouvelleAnnonce = new Annonce(titreAnnonce.getText().toString(), adresseAnnonce.getText().toString(), "", Double.parseDouble(prixAnnonce.getText().toString()));

                // On navigue vers la page d'accueil en passant la nouvelle annonce comme paramètre
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("nouvelleAnnonce", nouvelleAnnonce);
//                Navigation.findNavController(view).navigate(R.id.navigation_home, bundle);

                //On ajoute la nouvelle annonce à la DB
                dbManager.insert(nouvelleAnnonce);
                //On navigue vers la page de liste des annonces
                Navigation.findNavController(view).navigate(R.id.navigation_home);

            }
        });


        final Button boutonAppareilPhoto = binding.photoBtn;
        final Button boutonGalerie = binding.galerieBtn;
        final ImageView imageAnnonce = binding.imageAnnonce;

        boutonGalerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choisirImageDansGalerie();
            }
        });
        boutonAppareilPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prendrePhoto();
            }
        });



        activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        if(data != null) {
                            // On récupère l'URL de l'image
                            Uri selectedImageUri = data.getData();

                            if (selectedImageUri != null) {
                                // S'il y a bien une image, on l'affiche à l'écran
                                imageAnnonce.setImageURI(selectedImageUri);
                                imageAnnonce.setVisibility(View.VISIBLE);
                            } else {
                                // S'il n'y  a pas de chemin, c'est que l'image est stockée en BMP
                                // dans les données du résultat (appareil photo)
                                Bitmap photo = (Bitmap) data.getExtras().get("data");

                                if (photo == null) {
                                    return;
                                }

                                // S'il y a bien une photo, on l'affiche à l'écran
                                imageAnnonce.setImageBitmap(photo);
                                imageAnnonce.setVisibility(View.VISIBLE);
                            }
                        }

                    }
                }
            });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    // Gestion des images
    private void choisirImageDansGalerie(){
        // Create an instance of the Intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // Lancement de l'activité qui permet de sélectionner une image dans la galerie
        activityResultLauncher.launch(Intent.createChooser(i, "Select Picture"));

    }

    private void prendrePhoto(){
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Lancement de l'activité qui permet de prendre une photo
        activityResultLauncher.launch(Intent.createChooser(camera_intent, "Take Picture"));
    }
}