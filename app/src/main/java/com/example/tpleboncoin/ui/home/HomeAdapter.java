package com.example.tpleboncoin.ui.home;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpleboncoin.R;
import com.example.tpleboncoin.models.Annonce;
import com.example.tpleboncoin.ui.DetailScreen;

import java.util.ArrayList;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>  {
    private static final String TAG = "CustomAdapter";

    private static ArrayList<Annonce> mDataSet;
    private boolean mIsGrid;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titreTextView;
        private final TextView adresseTextView;
        private final TextView prixTextView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : mettre la page de Donia
                    Intent detailIntent = new Intent(v.getContext(), DetailScreen.class);
                    detailIntent.putExtra("annonce", mDataSet.get(getAdapterPosition()));
                    v.getContext().startActivity(detailIntent);
                    //detailIntent.putExtra("prix", prix);
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            titreTextView = (TextView) v.findViewById(R.id.titre_annonce);
            adresseTextView = (TextView) v.findViewById(R.id.adresse_annonce);
            prixTextView = (TextView) v.findViewById(R.id.prix_annonce);
        }

        public TextView getTitreTextView() {
            return titreTextView;
        }
        public TextView getAdresseTextView() {
            return adresseTextView;
        }
        public TextView getPrixTextView() {
            return prixTextView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     * @param isGrid boolean = le layout actuel (grille ou linéaire)
     */
    public HomeAdapter(ArrayList<Annonce> dataSet, boolean isGrid, Context context) {
        mDataSet = dataSet;
        mIsGrid = isGrid;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view. - Layout en fonction de la sélection
        View v;
        if (mIsGrid) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.annonces_grid, viewGroup, false);
        }
        else {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.annonces_liste, viewGroup, false);
        }

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTitreTextView().setText(mDataSet.get(position).getTitre());
        viewHolder.getAdresseTextView().setText(mDataSet.get(position).getAdresse());
        viewHolder.getPrixTextView().setText(String.format("%,.2f €", mDataSet.get(position).getPrix()));
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.toArray().length;
    }
}





