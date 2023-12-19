package org.jash.sprotnews2107.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jash.sprotnews2107.BR
import java.util.Date

@Entity
data class News(
    val content: String,
    val createTime: Date,
    val flag: Int,
    @PrimaryKey
    val id: Int,
    val imgurl: String,
    val looks: Int,
    val ntid: Int,
    val suid: Int,
    val title: String,

    val comments:Int,
    val collected:Int
) : BaseObservable() {
    @Bindable
    var collects:Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.collects)
        }
}