package com.projeto.todo.ui.recuperacaosenha

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projeto.todo.dto.GenericResponseDTO
import com.projeto.todo.service.AutenticacaoService
import kotlinx.coroutines.launch

class RecuperacaoSenhaViewModel : ViewModel() {
    private var autenticacaoService = AutenticacaoService()
    private var _response = MutableLiveData<GenericResponseDTO>()
    var response: LiveData<GenericResponseDTO> = _response

    fun enviarEmailRecuperacaoDeSenha(email: String) {
        viewModelScope.launch {
            _response.value = autenticacaoService.enviarEmailRecuperacaoDeSenha(email)
        }
    }
}