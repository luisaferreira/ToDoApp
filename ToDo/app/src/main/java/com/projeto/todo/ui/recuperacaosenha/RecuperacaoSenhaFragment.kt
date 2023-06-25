package com.projeto.todo.ui.recuperacaosenha

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.projeto.todo.R
import com.projeto.todo.databinding.FragmentRecuperacaoSenhaBinding
import com.projeto.todo.dto.RGB

class RecuperacaoSenhaFragment : Fragment() {
    private lateinit var binding: FragmentRecuperacaoSenhaBinding
    private lateinit var viewModel: RecuperacaoSenhaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecuperacaoSenhaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RecuperacaoSenhaViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRecuperarSenha.setOnClickListener {
            var email = binding.inputEmailRecuperacaoSenha.text.toString()

            if(email.isNullOrBlank())
                exibirSnackBar("Por favor, preencha o e-mail corretamente!", RGB(223, 40, 63))
            else
                viewModel.enviarEmailRecuperacaoDeSenha(email)
        }

        viewModel.response
            .observe(viewLifecycleOwner) { response ->
                if (response.Sucesso) {
                    println("enviou")
                } else {
                    exibirSnackBar(response.Mensagem.toString(), RGB(223, 40, 63))
                }
            }
    }

    private fun exibirSnackBar(mensagem: String, rgb: RGB) {
        Snackbar.make(binding.root, mensagem, Snackbar.LENGTH_LONG)
            .setBackgroundTint(Color.rgb(rgb.Red, rgb.Green, rgb.Blue))
            .show()
    }
}