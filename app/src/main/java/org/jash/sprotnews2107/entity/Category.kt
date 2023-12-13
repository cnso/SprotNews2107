package org.jash.sprotnews2107.entity

import android.content.Context
import android.widget.Toast

data class Category(
    val id: Int,
    val typename: String
) {
    fun showName(context:Context) {
        Toast.makeText(context, typename, Toast.LENGTH_SHORT).show()
    }
}