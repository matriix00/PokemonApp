package com.example.pokemonapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemonapp.R;
import com.example.pokemonapp.pojo.Pokemon;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private ArrayList<Pokemon>pokemons = new ArrayList<>();
    private Context mContext;
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item,parent,false));
    }

    public PokemonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.pokemonText.setText(pokemons.get(position).getName());
        Glide.with(mContext).load(pokemons.get(position).getUrl()).into(holder.pokemonImg);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
    public void setList(ArrayList<Pokemon>pokemonList){
        this.pokemons=pokemonList;
    }
    public Pokemon getPokemonAt(int position){
        return pokemons.get(position);
    }
    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImg;
        TextView pokemonText;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImg = itemView.findViewById(R.id.pokemon_img);
            pokemonText = itemView.findViewById(R.id.pokemon_name);
        }
    }
}
