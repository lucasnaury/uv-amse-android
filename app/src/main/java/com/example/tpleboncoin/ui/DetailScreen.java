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
                binding.prixTextView.setText( String.format("%,.2f €", annonce.getPrix())) ;
                binding.descriptionTextView4.setText(annonce.getDescription());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    binding.descriptionTextView4.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                }
                binding.titreTextView.setText(annonce.getTitre());
                binding.imageView.setImageURI(Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuN0ilDzXue08UjwoRi4jiqj0Y9FWs7jDhYY949clvcVYpoCUZzH1OkTKv5-FqBHDwKG0&usqp=CAU"));
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