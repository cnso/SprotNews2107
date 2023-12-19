package org.jash.sprotnews2107.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.reactivex.Observable
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.entity.User

@Dao
interface UserDao {
    @Query("select * from user where id=:id")
    fun findById(id:Int):Observable<User>
    @Upsert
    fun save(vararg user:User)
}