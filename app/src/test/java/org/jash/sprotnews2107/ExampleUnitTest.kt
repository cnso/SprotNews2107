package org.jash.sprotnews2107

import io.reactivex.schedulers.Schedulers
import org.jash.common.utils.retrofit
import org.jash.sprotnews2107.entity.User
import org.jash.sprotnews2107.net.NewsService
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun testNet() {
        val service = retrofit.create(NewsService::class.java)
//        val category = service.getAllCategory()
//        category
//            .subscribe ({
//            if (it.code == 0) {
//                it.data.forEach(::println)
//            } else {
//                println("网络出错")
//            }
//        }, {
//            it.printStackTrace()
//        })
//        service.getNewsByCategoryId(1, 1, 10).subscribe({
//            if (it.code == 0) {
//                it.data.records.forEach(::println)
//            } else {
//                println(it.msg)
//            }
//        }, {
//            it.printStackTrace()
//        })
        service.getUserAll1().zipWith(service.getUserAll2()) { r1, r2 ->
            if (r1.code == 0 && r2.code == 0) {
                r1.data.zip(r2.data) { u1, u2 ->
                    User(u1.birthday ?: u2.birthday, u1.createTime, Math.max(u1.flag, u2.flag), u1.id, u1.imgurl ?: u2.imgurl, u1.info ?: u2.info, u1.password ?: u2.password, u1.phone ?:u2.phone, u1.sex ?: u2.sex, u1.username ?: u2.username)
                }
            } else {
                listOf<User>()
            }
        }.subscribe({
                it.forEach(::println)
        },{
            it.printStackTrace()
        })

        Thread.sleep(2000)
    }

}

