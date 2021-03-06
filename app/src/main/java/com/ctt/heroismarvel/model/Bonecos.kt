package com.ctt.heroismarvel.model

import android.media.Image
import com.google.gson.annotations.SerializedName

class Bonecos (
    @SerializedName("name")
    val nome: String,

    @SerializedName("description")
    val descricao: String,

    @SerializedName("thumbnail")
    val imagem: Image,
)