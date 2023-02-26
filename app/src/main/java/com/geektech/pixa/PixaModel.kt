package com.geektech.pixa

data class PixaModel(
    var hits: ArrayList<Hit>
)

data class Hit(
    var largeImageUrl: String
)