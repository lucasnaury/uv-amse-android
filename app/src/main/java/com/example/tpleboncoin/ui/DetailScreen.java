package com.example.tpleboncoin.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tpleboncoin.databinding.ActivityDetailScreenBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
                binding.descriptionTextView4.setText(annonce.getDescription());
                binding.titreTextView.setText(annonce.getTitre());
            }
            binding.smsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent smsIntent = new Intent(Intent.ACTION_MAIN);
                    smsIntent.addCategory(Intent.CATEGORY_APP_MESSAGING);
                    startActivity(smsIntent);
                }
            });

        }
}