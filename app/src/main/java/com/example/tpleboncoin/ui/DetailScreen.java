package com.example.tpleboncoin.ui;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tpleboncoin.databinding.ActivityDetailScreenBinding;

import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tpleboncoin.R;
import com.example.tpleboncoin.models.Annonce;

import java.util.Objects;

public class DetailScreen extends AppCompatActivity {
        ActivityDetailScreenBinding binding;
        Annonce annonce;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_detail_screen);
            binding = ActivityDetailScreenBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            Intent intent = this.getIntent();
            if (intent != null){
                annonce = intent.getParcelableExtra("annonce");

                binding.adresseTextView.setText(annonce.getAdresse());
                binding.prixTextView.setText( String.format("%,.2f €", annonce.getPrix())) ;
                binding.descriptionTextView4.setText(annonce.getDescription());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    binding.descriptionTextView4.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                }
                binding.titreTextView.setText(annonce.getTitre());

                if(annonce.getImage() != null){
                    binding.imageView.setImageBitmap(annonce.getImageAsBitmap());
                }

            }

            //Only show buttons if data provided in annonce
            if(Objects.equals(annonce.getNumeroTelephone(), "")){
                binding.smsButton.setVisibility(View.INVISIBLE);
            }
            if(Objects.equals(annonce.getEmail(), "")){
                binding.emailButton.setVisibility(View.INVISIBLE);
            }

            //Buttons for SMS or email
            binding.smsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setData(Uri.parse("sms:"));
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra(Intent.EXTRA_TEXT, "");
                    smsIntent.putExtra("address",  annonce.getNumeroTelephone());
                    startActivity(smsIntent);
                }
            });


            binding.emailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", annonce.getEmail(), null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Réponse à l'annonce - " + annonce.getTitre());
//                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                    startActivity(Intent.createChooser(emailIntent, "Envoi d'email"));
                }
            });

        }
}