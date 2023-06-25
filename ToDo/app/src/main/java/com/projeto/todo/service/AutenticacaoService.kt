package com.projeto.todo.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.projeto.todo.data.entity.Usuario
import com.projeto.todo.dto.GenericResponseDTO
import kotlinx.coroutines.tasks.await

class AutenticacaoService {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun criarUsuarioComEmailESenha(usuario: Usuario) : GenericResponseDTO {
        var response = GenericResponseDTO(false, null)

        try {
            var task = auth.createUserWithEmailAndPassword(usuario.Email, usuario.Senha).await()
            var user = task.user
            var infoAdicional = userProfileChangeRequest {
                displayName = usuario.Nome
                photoUri = null
            }

            user?.updateProfile(infoAdicional)

            response.Sucesso = true
            response.Mensagem = "Usuário criado com sucesso!"

        } catch (e: Exception) {
            response.Mensagem = e.message
        }

        return response
    }

    suspend fun entrarComEmailESenha(email: String, senha: String) : GenericResponseDTO {
        var response = GenericResponseDTO(false, null)

        try {
            var task = auth.signInWithEmailAndPassword(email, senha).await()
            response.Sucesso = true
            response.Mensagem = "Login realizado com sucesso!"
        } catch(e: Exception) {
            response.Mensagem = e.message
        }

        return response
    }
}