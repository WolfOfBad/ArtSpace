package istu.bwoah.artspace

data class Artwork(val imageRes: Int, val title: String, val artist: String, val year: String)

val artworks = listOf(
    Artwork(R.drawable.mona_lisa, "Mona Lisa", "Leonardo da Vinci", "1503"),
    Artwork(R.drawable.starry_night, "Starry Night", "Vincent van Gogh", "1889"),
    Artwork(R.drawable.campbell_can, "Campbell's Soup Cans", "Andy Warhol", "1962")
)
