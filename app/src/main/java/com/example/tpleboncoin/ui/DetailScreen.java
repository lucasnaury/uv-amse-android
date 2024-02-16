package com.example.tpleboncoin.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tpleboncoin.databinding.ActivityDetailScreenBinding;

import android.content.Intent;
import android.os.Bundle;

import com.example.tpleboncoin.R;

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
                String titre = intent.getStringExtra("titre");
                String adresse = intent.getStringExtra("adresse");
                String description = intent.getStringExtra("description");
                Double prix = intent.getDoubleExtra("prix",999);


                binding.adresseTextView.setText(adresse);
                binding.prixTextView.setText(prix.toString()+" €");
                binding.imageView.setImageResource(R.drawable.ic_account_circle_black_24dp);
                binding.descriptionTextView4.setText(description);
                binding.titreTextView.setText(titre);
            }
        }
}