package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pokemonapp.adapters.PokemonAdapter;
import com.example.pokemonapp.pojo.Pokemon;
import com.example.pokemonapp.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class FavoriteActivity extends AppCompatActivity {

    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    Button toHomeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.fav_pokemon_rv);
        adapter = new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);
        setupSwipe();
        toHomeBtn = findViewById(R.id.to_home_btn);
        toHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriteActivity.this,MainActivity.class));
            }
        });

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.getFavPokemons();
        viewModel.getFavList().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                ArrayList<Pokemon> arrayListofPokemon = new ArrayList<>();
                arrayListofPokemon.addAll(pokemons);
                adapter.setList(arrayListofPokemon);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupSwipe() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemPosition =viewHolder.getAdapterPosition();
                Pokemon swipedPokemon = adapter.getPokemonAt(swipedPokemPosition);
                viewModel.deletePokemon(swipedPokemon.getName());
                adapter.notifyDataSetChanged();
                Toast.makeText(FavoriteActivity.this, "Pokemon deleted from fav Database", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}