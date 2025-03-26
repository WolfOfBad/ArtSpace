package istu.bwoah.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
            val configuration = LocalConfiguration.current
            val isTablet = configuration.screenWidthDp >= 600
            val isPhone = configuration.screenHeightDp <= 600

            ArtSpaceTheme {
                ArtSpaceApp(isTablet, isPhone)
            }
        }
    }
}

@Composable
fun ArtSpaceApp(isTablet: Boolean, isPhone: Boolean) {
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(if (isTablet && isPhone) 4.dp else 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        ArtImage(isTablet, currentIndex)

        Spacer(modifier = Modifier.height(if (isTablet && isPhone) 5.dp else 40.dp))

        ArtistInfo(isTablet, isPhone, currentIndex)

        Spacer(modifier = Modifier.height(if (isTablet or (isTablet && isPhone)) 5.dp else 20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (isTablet && isPhone) 5.dp else 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.size(
                    width = if (isTablet) 160.dp else 100.dp,
                    height = if (isTablet) 60.dp else 40.dp
                ),
                onClick = { currentIndex = (currentIndex + artworks.size - 1) % artworks.size }
            ) {
                Text(
                    text = "Back",
                    fontSize = if (isTablet) 20.sp else 16.sp
                )
            }
            Button(
                modifier = Modifier.size(
                    width = if (isTablet) 160.dp else 100.dp,
                    height = if (isTablet) 60.dp else 40.dp
                ),
                onClick = { currentIndex = ++currentIndex % artworks.size }
            ) {
                Text(
                    text = "Next",
                    fontSize = if (isTablet) 20.sp else 16.sp
                )
            }
        }
    }
}

@Composable
fun ArtImage(isTablet: Boolean, currentIndex: Int) {
    Surface(
        modifier = Modifier
            .fillMaxHeight(if (isTablet) 0.65f else 0.6f)
            .aspectRatio(3f / 4f),
        shadowElevation = 16.dp,
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = artworks[currentIndex].imageRes),
            contentDescription = artworks[currentIndex].title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun ArtistInfo(isTablet: Boolean, isPhone: Boolean, currentIndex: Int) {
    Column(
        Modifier
            .background(color = Color(0xFFdbdbdb))
            .padding(if (isTablet && isPhone) 1.dp else 8.dp)
    )
    {
        Text(
            text = artworks[currentIndex].title,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = if (isTablet && isPhone) 16.sp else 24.sp
            )
        )
        Spacer(modifier = Modifier.size(if (isTablet && isPhone) 0.dp else 2.dp))
        Text(
            text = "${artworks[currentIndex].artist}, ${artworks[currentIndex].year}",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = if (isTablet && isPhone) 12.sp else 20.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp(isTablet = false, isPhone = true)
    }
}