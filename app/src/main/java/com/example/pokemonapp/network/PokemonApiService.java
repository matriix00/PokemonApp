package com.example.pokemonapp.network;

import com.example.pokemonapp.pojo.PokemonResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApiService {
@GET("pokemon/")
    Observable<PokemonResponse>getPokemons();
}
