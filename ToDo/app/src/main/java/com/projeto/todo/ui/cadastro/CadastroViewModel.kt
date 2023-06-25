package com.projeto.todo.ui.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.projeto.todo.data.entity.Usuario
import com.projeto.todo.dto.GenericResponseDTO
import com.projeto.todo.service.AutenticacaoService
import kotlinx.coroutines.launch

class CadastroViewModel : ViewModel() {
    private var autenticacaoService = AutenticacaoService()
    private var _response = MutableLiveData<GenericResponseDTO>()
    var response: LiveData<GenericResponseDTO> = _response

    fun criarUsuarioComEmailESenha(usuario: Usuario) {
        viewModelScope.launch {
            _response.value = autenticacaoService.criarUsuarioComEmailESenha(usuario)
        }
    }

}