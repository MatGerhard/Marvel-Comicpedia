package matheus.luna.app.marvel.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import matheus.luna.app.marvel.data.model.character.CharacterModel
import matheus.luna.app.marvel.repository.MarvelRepository
import matheus.luna.app.marvel.ui.state.ResourceState
import javax.inject.Inject

@HiltViewModel
class FavoriteCharacterViewModel @Inject constructor(

    private val repository: MarvelRepository

) : ViewModel() {

    private val _favorite =
        MutableStateFlow<ResourceState<List<CharacterModel>>>(ResourceState.Empty())
    val favorite: StateFlow<ResourceState<List<CharacterModel>>> = _favorite

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        repository.getAll().collectLatest {
            if (it.isNullOrEmpty()) {
                _favorite.value = ResourceState.Empty()
            } else {
                _favorite.value = ResourceState.Sucess(it)
            }
        }
    }

    fun delete(characterModel: CharacterModel) = viewModelScope.launch {
        repository.delete(characterModel)
    }
}