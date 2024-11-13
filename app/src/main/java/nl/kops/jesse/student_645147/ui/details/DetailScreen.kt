package nl.kops.jesse.student_645147.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.kops.jesse.student_645147.R
import nl.kops.jesse.student_645147.ui.components.DetailRow

@Composable
fun DetailScreen(navController: NavController, pokemonId: Int, viewModel: PokemonDetailViewModel) {
    LaunchedEffect(pokemonId) {
        viewModel.fetchPokemonDetail(pokemonId)
    }

    val pokemonDetail = viewModel.pokemonDetail.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value
    val isFavorite = remember { mutableStateOf(false) }

    LaunchedEffect(pokemonId) {
        viewModel.favoritesFlow.collect { favorites ->
            isFavorite.value = favorites.contains(pokemonId.toString())
        }
    }

    if (errorMessage != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { viewModel.retryFetchingPokemonDetail(pokemonId) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0E0940))
                ) {
                    Text("Retry")
                }
            }
        }
    } else if (pokemonDetail == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEDF6FF))
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back Icon",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Back",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color(0xFF0E0940),
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            if (isFavorite.value) {
                                viewModel.removeFromFavorites(pokemonId)
                            } else {
                                viewModel.addToFavorites(pokemonId)
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_favorites),
                            contentDescription = "Favorites Icon",
                            modifier = Modifier.size(24.dp),
                            colorFilter = if (isFavorite.value) ColorFilter.tint(Color.Red) else null
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = pokemonDetail.name.capitalize(),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        ),
                        color = Color(0xFF0E0940)
                    )
                    Text(
                        text = "#${pokemonDetail.id.toString().padStart(3, '0')}",
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.Gray)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pokemonDetail.types.forEach { type ->
                        val typeColor = getTypeColor(type)
                        Box(
                            modifier = Modifier
                                .background(typeColor, shape = RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = type.capitalize(),
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonDetail.id}.png",
                        contentDescription = "Pokemon Artwork",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("About", fontWeight = FontWeight.Bold, color = Color(0xFF0E0940))
                        Text("Stats", color = Color.Gray)
                        Text("Evolution", color = Color.Gray)
                    }

                    Divider(color = Color(0xFF0E0940), thickness = 1.dp)

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        DetailRow(label = "Name:", value = pokemonDetail.name.capitalize())
                        DetailRow(label = "ID:", value = pokemonDetail.id.toString())
                        DetailRow(label = "Weight:", value = "${pokemonDetail.weight} kg")
                        DetailRow(label = "Height:", value = "${pokemonDetail.height} m")
                        DetailRow(
                            label = "Types:",
                            value = pokemonDetail.types.joinToString().capitalize()
                        )
                        DetailRow(
                            label = "Abilities:",
                            value = pokemonDetail.abilities.joinToString().capitalize()
                        )
                    }
                }
            }
        }
    }
}

fun getTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "grass" -> Color(0xFF78C850)
        "poison" -> Color(0xFFA040A0)
        "fire" -> Color(0xFFF08030)
        "water" -> Color(0xFF6890F0)
        "bug" -> Color(0xFFA8B820)
        else -> Color.Gray
    }
}
