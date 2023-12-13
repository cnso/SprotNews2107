package org.jash.sprotnews2107.entity

data class Page<D>(
    val countId: Any,
    val current: Int,
    val maxLimit: Any,
    val optimizeCountSql: Boolean,
    val orders: List<Any>,
    val pages: Int,
    val searchCount: Boolean,
    val size: Int,
    val total: Int,
    val records:List<D>
)