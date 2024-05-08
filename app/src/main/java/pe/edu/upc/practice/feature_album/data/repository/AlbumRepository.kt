package pe.edu.upc.practice.feature_album.data.repository

import android.util.Log
import pe.edu.upc.practice.feature_album.data.remote.AlbumResponse
import pe.edu.upc.practice.feature_album.data.remote.AlbumService
import pe.edu.upc.practice.feature_album.data.remote.AlbumServiceFactory
import pe.edu.upc.practice.feature_album.domain.model.Album
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository (
    private val albumService: AlbumService = AlbumServiceFactory.getAlbumService()
) {
    fun getAll(callback: (List<Album>) -> Unit) {
        val getAll = albumService.getAll()

        getAll.enqueue(object: Callback<List<AlbumResponse>> {
            override fun onResponse(
                call: Call<List<AlbumResponse>>,
                response: Response<List<AlbumResponse>>
            ) {
                if (response.isSuccessful) {
                    val albumsResponse = response.body() ?: emptyList()
                    val albums: List<Album> = albumsResponse.map { albumResponse ->
                        Album(
                            id = albumResponse.id,
                            strAlbum = albumResponse.strAlbum,
                            strArtist = albumResponse.strArtist,
                            strAlbumThumb = albumResponse.strAlbumThumb,
                            intYearReleased = albumResponse.intYearReleased,
                            strAlbum3DCase = albumResponse.strAlbum3DCase,
                            strGenre = albumResponse.strGenre
                        )
                    }
                    callback(albums)
                }
            }

            override fun onFailure(call: Call<List<AlbumResponse>>, t: Throwable) {
                t.message?.let {
                    Log.d("AlbumRepository", it)
                }
            }
        })
    }

    fun getAlbumById(id: Int, callback: (Album) -> Unit) {
        val getAlbumById = albumService.getAlbumById(id = id)

        getAlbumById.enqueue(object: Callback<Album> {
            override fun onResponse(
                call: Call<Album>,
                response: Response<Album>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { album ->
                        callback(album)
                    }
                }
            }

            override fun onFailure(call: Call<Album>, t: Throwable) {
                t.message?.let {
                    Log.d("AlbumRepository", it)
                }
            }
        })
    }
}