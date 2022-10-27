package matheus.luna.app.marvel.data.model.comic

import com.google.gson.annotations.SerializedName
import matheus.luna.app.marvel.data.model.thumbnail.ThumbnailModel
import java.io.Serializable

data class ComicModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel
) : Serializable