package pe.edu.upc.practice.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.glide.GlideImage
import pe.edu.upc.practice.ui.favouritealbum.FavouriteAlbum
import pe.edu.upc.practice.ui.listalbum.ListAlbum

@Composable
fun Home(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "ViewHome"){
        composable("ViewHome"){
            ViewHome(
                navigateInicio = { navController.navigate("ListAlbum") },
                navigateFinal = { navController.navigate("FavouriteAlbum") }
            )
        }
        composable("ListAlbum"){
            ListAlbum {
                
            }
        }
        composable("FavouriteAlbum"){
            FavouriteAlbum {

            }
        }
    }
}

@Composable
fun ViewHome(
    navigateInicio: () -> Unit = {},
    navigateFinal: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "App Super Zound",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(16.dp)
        )

        GlideImage(
            imageModel = { "https://www.womenseday.org/wp-content/uploads/2017/11/UPCMini-1.jpg" },
            modifier = Modifier
                .size(500.dp)
                .clip(shape = RoundedCornerShape(4.dp))
        )

        Row(
            modifier = modifier
                .padding(horizontal = 6.dp)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                onClick = { navigateInicio() }
            ) {
                Text(text = "List Album")
            }

            Button(
                modifier = modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                onClick = { navigateFinal() }
            ) {
                Text(text = "Favourite Album")
            }
        }
    }
}