package com.example.tpleboncoin.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tpleboncoin.databinding.ActivityDetailScreenBinding;

import android.content.Intent;
import android.os.Bundle;

import com.example.tpleboncoin.R;
import com.example.tpleboncoin.models.Annonce;

public class DetailScreen extends AppCompatActivity {
        ActivityDetailScreenBinding binding;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_detail_screen);
            binding = ActivityDetailScreenBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            Intent intent = this.getIntent();
            if (intent != null){
                Annonce annonce = intent.getParcelableExtra("annonce");

                binding.adresseTextView.setText(annonce.getAdresse());
                binding.prixTextView.setText( Double.toString(annonce.getPrix()) + " €");
                binding.imageView.setImageResource(R.drawable.ic_account_circle_black_24dp);
                binding.descriptionTextView4.setText(annonce.);
                binding.titreTextView.setText(titre);
            }
        }
}