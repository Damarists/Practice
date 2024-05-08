package pe.edu.upc.practice.ui.favouritealbum

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import pe.edu.upc.practice.feature_album.data.repository.AlbumRepository
import pe.edu.upc.practice.feature_album.domain.model.Album
import pe.edu.upc.practice.ui.share.AlbumTopAppBar

@Composable
fun FavouriteAlbum(selectAlbum: (Album) -> Unit){
    Scaffold(
        topBar = {
            AlbumTopAppBar("")
        },
    ) {  paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)){
            SearchF()
            Text(
                text = "All Albums",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(16.dp)
            )
            AlbumsList(selectAlbum = selectAlbum)
        }
    }
}

@Composable
fun SearchF(){
    var searchtext by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        placeholder = { Text("Search") },
        value = searchtext,
        onValueChange = { newValue -> searchtext = newValue },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}

@Composable
fun AlbumsList(albumRepository: AlbumRepository = AlbumRepository(), selectAlbum: (Album) -> Unit){
    val albums = remember {
        mutableStateOf(emptyList<Album>())
    }

    albumRepository.getAll {
        albums.value = it
    }

    try {
        albumRepository.getAll { albumList ->
            albums.value = albumList
        }
    } catch (e: Exception) {
        Log.e("AlbumsList", "Error al obtener la lista de albums: ${e.message}")
    }

    LazyColumn {
        items(albums.value) { album ->
            AlbumItem(album, selectAlbum)
        }
    }
}

@Composable
fun AlbumItem(album: Album, selectAlbum: (Album) -> Unit){
    val name = album.strAlbum
    val artista = album.strArtist

    Row(
        modifier = Modifier
            .clickable(onClick = { selectAlbum(album) })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "$name", fontWeight = FontWeight.Bold)
            Text(text = "Artista: $artista")
        }

        GlideImage(
            imageModel = { album.strAlbumThumb },
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
    }
}