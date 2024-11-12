package nl.kops.jesse.student_645147.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.kops.jesse.student_645147.R

@Composable
fun DetailScreen(navController: NavController, pokemonId: Int, viewModel: PokemonDetailViewModel) {
    LaunchedEffect(pokemonId) {
        viewModel.fetchPokemonDetail(pokemonId)
    }

    val pokemonDetail = viewModel.pokemonDetail.collectAsState().value

    if (pokemonDetail == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { navController.popBackStack() }) {
                    Text(text = "Back", style = MaterialTheme.typography.bodyMedium)
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_favorites),
                    contentDescription = "Favorites Icon",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = pokemonDetail.name, style = MaterialTheme.typography.titleLarge)
                Text(text = "#${pokemonDetail.id}", style = MaterialTheme.typography.titleLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Types: ${pokemonDetail.types.joinToString()}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonDetail.id}.png",
                contentDescription = "Pokemon Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "About", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Height: ${pokemonDetail.height} m",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Types: ${pokemonDetail.types.joinToString()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Abilities: ${pokemonDetail.abilities.joinToString()}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
