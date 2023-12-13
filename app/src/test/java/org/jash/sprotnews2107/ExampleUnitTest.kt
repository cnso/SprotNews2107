package org.jash.sprotnews2107

import io.reactivex.schedulers.Schedulers
import org.jash.common.utils.retrofit
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
        val category = service.getAllCategory()
        category
            .subscribe ({
            if (it.code == 200) {
                it.data.forEach(::println)
            } else {
                println("网络出错")
            }
        }, {
            it.printStackTrace()
        })
//        Thread.sleep(2000)
    }
}