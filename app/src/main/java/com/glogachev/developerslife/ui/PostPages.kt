package com.glogachev.developerslife.ui

enum class PostPages(val title: String, val position: Int) {
    LATEST("Последние", LATEST_POS),
    HOT("Горячее", HOT_POS),
    TOP("Лучшее", TOP_POS),
    RANDOM("Случайное", RANDOM_POS)
}

private const val LATEST_POS = 0
private const val HOT_POS = 1
private const val TOP_POS = 2
private const val RANDOM_POS = 3
