package pe.edu.upc.practice.feature_album.data.remote

data class AlbumResponse (
    val id: Int,
    val strAlbum: String,
    val strArtist: String,
    val strAlbumThumb: String,
    val intYearReleased: Int,
    val strAlbum3DCase: String,
    val strGenre: String,
)