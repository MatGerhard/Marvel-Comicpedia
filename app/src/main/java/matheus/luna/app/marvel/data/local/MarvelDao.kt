package matheus.luna.app.marvel.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import matheus.luna.app.marvel.data.model.character.CharacterModel

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(characterModel: CharacterModel)

    @Query("SELECT * FROM characterModel ORDER BY id")
    fun getAll(): Flow<List<CharacterModel>>

    @Delete
    suspend fun delete(characterModel: CharacterModel)
}