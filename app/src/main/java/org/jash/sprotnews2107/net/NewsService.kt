package org.jash.sprotnews2107.net

import io.reactivex.Observable
import org.jash.sprotnews2107.entity.Category
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.entity.Page
import org.jash.sprotnews2107.entity.Res
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/api/nt/all")
    fun getAllCategory():Observable<Res<List<Category>>>
    @GET("/api/news/page")
    fun getNewsByCategoryId(@Query("type") type:Int,@Query("page") page:Int,@Query("size") size:Int):Observable<Res<Page<News>>>
}