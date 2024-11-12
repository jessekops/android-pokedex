package nl.kops.jesse.student_645147.ui.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.kops.jesse.student_645147.ui.components.PokemonCard
import nl.kops.jesse.student_645147.ui.components.SearchBar

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    val pokemonList = viewModel.pokemonList.collectAsState().value
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDF6FF))
    ) {
        SearchBar(
            placeholderText = "Search for Pokémon...",
            onSearchQueryChanged = { query ->
                searchQuery = query
                viewModel.searchPokemon(query)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "All Pokémon",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0E0940)
            ),
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pokemonList.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }) { pokemon ->
                PokemonCard(
                    pokemonId = pokemon.id.toString().padStart(3, '0'),
                    pokemonName = pokemon.name.capitalize(),
                    imageUrl = pokemon.imageUrl,
                    onClick = {
                        println("PokemonCard clicked: ${pokemon.id}")
                        // Log the Pokemon ID using Log.d
                        Log.d("PokemonCard", "Navigating to detailscreen with Pokemon ID: ${pokemon.id}")

                        navController.navigate("detailscreen/${pokemon.id}")

                    }
                )
            }
        }
    }
}


