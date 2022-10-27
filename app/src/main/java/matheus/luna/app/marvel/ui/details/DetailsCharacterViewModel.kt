package matheus.luna.app.marvel.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import matheus.luna.app.marvel.data.model.character.CharacterModel
import matheus.luna.app.marvel.data.model.comic.ComicModelResponse
import matheus.luna.app.marvel.repository.MarvelRepository
import matheus.luna.app.marvel.ui.state.ResourceState
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsCharacterViewModel @Inject constructor(

    private val repository: MarvelRepository

) : ViewModel() {

    private val _details =
        MutableStateFlow<ResourceState<ComicModelResponse>>(ResourceState.Loading())
    val details: StateFlow<ResourceState<ComicModelResponse>> = _details

    fun fetch(chacarcterId: Int) = viewModelScope.launch {
        safeFetch(chacarcterId)
    }

    private suspend fun safeFetch(chacarcterId: Int) {
        _details.value = ResourceState.Loading()

        try {
            val response = repository.getComics(chacarcterId)
            _details.value = handleResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _details.value = ResourceState.Error("Erro de rede")
                else -> _details.value = ResourceState.Error("Erro de conversão")
            }
        }
    }

    private fun handleResponse(response: Response<ComicModelResponse>): ResourceState<ComicModelResponse> {
        if (response.isSuccessful)
            response.body()?.let { values ->
                return ResourceState.Sucess(values)
            }
        return ResourceState.Error(response.message())
    }

    fun insert(characterModel: CharacterModel) = viewModelScope.launch {
        repository.insert(characterModel)
    }
}