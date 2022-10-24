package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
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

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    Button toFavBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.pokemon_rv);
        adapter = new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);
        setupSwipe();
        toFavBtn = findViewById(R.id.to_fav_btn);
        toFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FavoriteActivity.class));
            }
        });
        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getPokemons();
        viewModel.getPokemonList().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                adapter.setList(pokemons);
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void setupSwipe(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemPosition =viewHolder.getAdapterPosition();
                Pokemon swipedPokemon = adapter.getPokemonAt(swipedPokemPosition);
                viewModel.insertPokemon(swipedPokemon);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Pokemon added to fav Database", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}