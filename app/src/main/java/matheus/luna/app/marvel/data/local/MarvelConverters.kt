package matheus.luna.app.marvel.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import matheus.luna.app.marvel.data.model.thumbnail.ThumbnailModel

class MarvelConverters {

    @TypeConverter
    fun fromThumbnail(thumbnailModel: ThumbnailModel): String = Gson().toJson(thumbnailModel)

    @TypeConverter
    fun toThumbnail(thumbnailModel: String): ThumbnailModel =
        Gson().fromJson(thumbnailModel, ThumbnailModel::class.java)
}