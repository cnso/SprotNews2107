package org.jash.sprotnews2107.entity

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.Ignore
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
    @Ignore
    var key:String? = null
    @Bindable
    var collects:Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.collects)
        }
    @get:Ignore
    val heightTitle:CharSequence?
        get() = key?.let {
            val ss = SpannableString(title)
            it.toRegex().findAll(title).forEach { matchResult ->
                ss.setSpan(ForegroundColorSpan(Color.BLUE), matchResult.range.first, matchResult.range.last + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
            ss
        }
}