package com.example.pokemonapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokemonapp.pojo.Pokemon;

@Database(entities = Pokemon.class,version = 1,exportSchema = false)
abstract public class PokemonDB extends RoomDatabase {
    public abstract PokemonDao pokemonDao();

}
