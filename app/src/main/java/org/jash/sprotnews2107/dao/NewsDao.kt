package org.jash.sprotnews2107.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.reactivex.Observable
import org.jash.sprotnews2107.entity.News

@Dao
interface NewsDao {
    @Query("select * from news where id=:id")
    fun findById(id:Int):Observable<News>
    @Upsert
    fun save(vararg news:News)
}