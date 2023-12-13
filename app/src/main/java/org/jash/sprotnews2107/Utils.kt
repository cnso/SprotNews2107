package org.jash.sprotnews2107

import android.content.Context
import org.jash.sprotnews2107.dao.NewsDatabase

val Context.database:NewsDatabase
    get() = (applicationContext as App).database