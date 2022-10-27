package matheus.luna.app.marvel.repository

import matheus.luna.app.marvel.data.local.MarvelDao
import matheus.luna.app.marvel.data.model.character.CharacterModel
import matheus.luna.app.marvel.data.remote.ServiceAPI
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val api: ServiceAPI,
    private val dao: MarvelDao
) {
    suspend fun list(nameStartsWith: String? = null) = api.list(nameStartsWith)
    suspend fun getComics(characterId: Int) = api.getComics(characterId)

    suspend fun insert(characterModel: CharacterModel) = dao.updateInsert(characterModel)
    fun getAll() = dao.getAll()
    suspend fun delete(characterModel: CharacterModel) = dao.delete(characterModel)
}