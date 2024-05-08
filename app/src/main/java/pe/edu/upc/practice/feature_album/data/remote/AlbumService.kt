package pe.edu.upc.practice.feature_album.data.remote

import pe.edu.upc.practice.feature_album.domain.model.Album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {
    @GET("albums")
    fun getAll(): Call<List<AlbumResponse>>

    @GET("albums/{albumId}")
    fun getAlbumById(@Path("albumId") id: Int): Call<Album>

    @GET("mostloved.php")
    fun fetchAlbums(@Query("format") format: String) : Call<AlbumResponse>
}