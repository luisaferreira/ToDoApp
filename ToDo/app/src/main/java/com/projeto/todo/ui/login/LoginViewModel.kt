package com.projeto.todo.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projeto.todo.dto.GenericResponseDTO
import com.projeto.todo.service.AutenticacaoService
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var autenticacaoService = AutenticacaoService()
    private var _response = MutableLiveData<GenericResponseDTO>()
    var response: LiveData<GenericResponseDTO> = _response

    fun entrarComEmailESenha(email: String, senha: String) {
        viewModelScope.launch {
            _response.value = autenticacaoService.entrarComEmailESenha(email, senha)
        }
    }
}