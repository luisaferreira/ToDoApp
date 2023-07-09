package com.projeto.todo.data.entity

data class Tarefa(
    var UidUsuario: String,
    var Nome: String,
    var Descricao: String?,
    var Data: String?,
    var Realizado: Boolean
)
