package istu.bwoah.artspace

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import istu.bwoah.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtImage(isPortrait, currentIndex)

            Spacer(modifier = Modifier.height(15.dp))

            ArtistInfo(currentIndex)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (isPortrait) 20.dp else 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier.size(
                    width = if (!isPortrait) 160.dp else 100.dp,
                    height = if (!isPortrait) 60.dp else 40.dp
                ),
                onClick = { currentIndex = (currentIndex + artworks.size - 1) % artworks.size }
            ) {
                Text(
                    text = "Back",
                    fontSize = if (!isPortrait) 20.sp else 16.sp
                )
            }
            Button(
                modifier = Modifier.size(
                    width = if (!isPortrait) 160.dp else 100.dp,
                    height = if (!isPortrait) 60.dp else 40.dp
                ),
                onClick = { currentIndex = ++currentIndex % artworks.size }
            ) {
                Text(
                    text = "Next",
                    fontSize = if (!isPortrait) 20.sp else 16.sp
                )
            }
        }
    }
}


@Composable
fun ArtImage(isPortrait: Boolean, currentIndex: Int) {
    val maxHeight = if (isPortrait) 600.dp else 220.dp
    val maxWidth = if (isPortrait) 350.dp else 400.dp
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .sizeIn(maxHeight = maxHeight, maxWidth = maxWidth)
            .shadow(16.dp, shape = RoundedCornerShape(8.dp)),
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = artworks[currentIndex].imageRes),
            contentDescription = artworks[currentIndex].title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun ArtistInfo(currentIndex: Int) {
    Column(
        Modifier
            .background(color = Color(0xFFdbdbdb))
            .padding(5.dp)
    )
    {
        Text(
            text = artworks[currentIndex].title,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = "${artworks[currentIndex].artist}, ${artworks[currentIndex].year}",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 18.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}