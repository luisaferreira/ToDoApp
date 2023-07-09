package com.projeto.todo.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.projeto.todo.data.entity.Tarefa
import com.projeto.todo.dto.GenericResponseDTO
import kotlinx.coroutines.tasks.await

class TarefaRepository {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun AdicionarTarefa(tarefa: Tarefa) : GenericResponseDTO {
        var response = GenericResponseDTO(false, null)

        try {
            var task = firestore.collection("Tarefas").add(tarefa).await()
            println(task.id)
            response.Sucesso = true
            response.Mensagem = "Tarefa adicionada com sucesso!"
        } catch (e: Exception) {
            response.Mensagem = e.message
        }

        return response
    }

}