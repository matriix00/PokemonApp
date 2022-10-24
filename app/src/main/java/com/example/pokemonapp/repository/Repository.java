package com.example.pokemonapp.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemonapp.db.PokemonDao;
import com.example.pokemonapp.network.PokemonApiService;
import com.example.pokemonapp.pojo.Pokemon;
import com.example.pokemonapp.pojo.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private PokemonApiService pokemonApiService;
    private PokemonDao pokemonDao;
    @Inject
    public Repository(PokemonApiService pokemonApiService,PokemonDao pokemonDao) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonDao = pokemonDao;
    }
    public Observable<PokemonResponse> getPokemons(){
        return pokemonApiService.getPokemons();
    }

    //room
    public void insertPokemon(Pokemon pokemon){
        pokemonDao.insertPokemon(pokemon);
    }
    public void deletePokemon(String name){
        pokemonDao.deletePokemon(name);
    }
    public LiveData<List<Pokemon>>getPokemnList(){
        return pokemonDao.getPokemons();
    }
}
