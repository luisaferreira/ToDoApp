package com.projeto.todo.ui.adicionartarefa

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.projeto.todo.R
import com.projeto.todo.data.entity.Tarefa
import com.projeto.todo.databinding.FragmentAdicionarTarefaBinding
import com.projeto.todo.dto.RGB

class AdicionarTarefaFragment : Fragment() {
    private lateinit var binding: FragmentAdicionarTarefaBinding
    private lateinit var navigation: NavController
    private lateinit var viewModel: AdicionarTarefaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdicionarTarefaBinding.inflate(inflater, container, false)
        navigation = findNavController()
        viewModel = ViewModelProvider(this)[AdicionarTarefaViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdicionarTarefa.setOnClickListener {
            var tarefa = validarFormulario()

            if (tarefa == null)
                exibirSnackBar("Por favor, preencha as informações corretamente!", RGB(223, 40, 63))
            else
                viewModel.adicionarTarefa(tarefa)
        }

        viewModel.response
            .observe(viewLifecycleOwner) { response ->
                if(response.Sucesso) {
                    navigation.navigate(R.id.action_adicionarTarefaFragment_to_tarefasFragment)
                } else {
                    exibirSnackBar(response.Mensagem.toString(), RGB(223, 40, 63))
                }
            }
    }

    private fun validarFormulario() : Tarefa? {
        var nomeTarefa = binding.inputNomeTarefa.text.toString()
        var descricaoTarefa = binding.inputDescricaoTarefa.text.toString()

        if(nomeTarefa.isNullOrBlank() || descricaoTarefa.isNullOrBlank())
            return null

        var tarefa = Tarefa("", nomeTarefa, descricaoTarefa, "", false)
        return tarefa
    }

    private fun exibirSnackBar(mensagem: String, rgb: RGB) {
        Snackbar.make(binding.root, mensagem, Snackbar.LENGTH_LONG)
            .setBackgroundTint(Color.rgb(rgb.Red, rgb.Green, rgb.Blue))
            .show()
    }
}