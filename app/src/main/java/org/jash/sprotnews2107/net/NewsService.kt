package org.jash.sprotnews2107.net

import io.reactivex.Observable
import org.jash.sprotnews2107.entity.Category
import org.jash.sprotnews2107.entity.Comment
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.entity.Page
import org.jash.sprotnews2107.entity.Res
import org.jash.sprotnews2107.entity.User
import org.jash.sprotnews2107.entity.Video
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsService {
    @GET("/api/nt/all")
    fun getAllCategory():Observable<Res<List<Category>>>
    @GET("/api/news/page")
    fun getNewsByCategoryId(@Query("type") type:Int,@Query("page") page:Int,@Query("size") size:Int):Observable<Res<Page<News>>>
    @POST("/api/user/loginname")
    fun login(@Body user:Map<String, String>):Observable<Res<String>>
    @GET("/api/user/loginout")
    fun logout():Observable<Res<Any?>>
    @GET("/api/news/detail")
    fun getNewsDetail(@Query("id") id:Int):Observable<Res<News>>
    @GET("/api/sms/sendlcode")
    fun getLoginCode(@Query("phone") phone:String):Observable<Res<Int>>
    @POST("/api/user/logincode")
    fun loginForPhone(@Body user:Map<String, String>):Observable<Res<String>>
    @GET("/api/newsCollect/my")
    fun getMyCollect():Observable<Res<List<News>>>
    @GET("/api/newsCollect/collect")
    fun collect(@Query("nid") nid:Int):Observable<Res<String>>
    @POST("/api/newsComment/save")
    fun postComment(@Body comment:Comment):Observable<Res<Any?>>
    @GET("/api/user/all")
    fun getUserAll1():Observable<Res<List<User>>>
    @GET("/api/ud/all.do")
    fun getUserAll2():Observable<Res<List<User>>>
    @GET("/api/newsComment/comments")
    fun getCommentByNid(@Query("nid") nid:Int):Observable<Res<List<Comment>>>
    @GET("/api/video/page?order=id")
    fun getVideo(@Query("page") page:Int,@Query("size") size:Int):Observable<Res<Page<Video>>>

}