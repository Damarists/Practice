package pe.edu.upc.practice.feature_album.data.remote

import pe.edu.upc.practice.network.RetrofitFactory

class AlbumServiceFactory {
    companion object {
        fun getAlbumService(): AlbumService {
            return RetrofitFactory.getRetrofit().create(AlbumService::class.java)
        }
    }
}