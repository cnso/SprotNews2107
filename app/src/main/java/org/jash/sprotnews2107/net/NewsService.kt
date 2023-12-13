package org.jash.sprotnews2107.net

import io.reactivex.Observable
import org.jash.sprotnews2107.entity.Category
import org.jash.sprotnews2107.entity.Res
import retrofit2.http.GET

interface NewsService {
    @GET("/news/type")
    fun getAllCategory():Observable<Res<List<Category>>>
}