package com.projeto.todo.ui.adicionartarefa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projeto.todo.data.entity.Tarefa
import com.projeto.todo.data.repository.TarefaRepository
import com.projeto.todo.dto.GenericResponseDTO
import com.projeto.todo.service.AutenticacaoService
import kotlinx.coroutines.launch

class AdicionarTarefaViewModel : ViewModel() {
    private var tarefaRepository = TarefaRepository()
    private var autenticacaoService = AutenticacaoService()
    private var _response = MutableLiveData<GenericResponseDTO>()
    var response: LiveData<GenericResponseDTO> = _response

    fun adicionarTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            tarefa.UidUsuario = autenticacaoService.obterUidUsuarioLogado().toString()
            _response.value = tarefaRepository.AdicionarTarefa(tarefa)
        }
    }
}