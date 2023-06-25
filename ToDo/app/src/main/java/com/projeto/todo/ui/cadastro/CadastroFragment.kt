package com.projeto.todo.ui.cadastro

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
import com.projeto.todo.data.entity.Usuario
import com.projeto.todo.databinding.FragmentCadastroBinding
import com.projeto.todo.dto.RGB

class CadastroFragment : Fragment() {
    private lateinit var binding: FragmentCadastroBinding
    private lateinit var navigation: NavController
    private lateinit var viewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        navigation = findNavController()
        viewModel = ViewModelProvider(this)[CadastroViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewLogin.setOnClickListener { navigation.navigate(R.id.action_cadastroFragment_to_loginFragment) }
        binding.btnVoltarCadastro.setOnClickListener { navigation.navigate(R.id.action_cadastroFragment_to_homeFragment) }
        binding.btnCadastro.setOnClickListener {
            var usuario = validarInfoUsuario()

            if(usuario == null)
                exibirSnackBar("Por favor, preencha as informações corretamente!", RGB(223, 40, 63))
            else
                viewModel.criarUsuarioComEmailESenha(usuario)
        }

        viewModel.response
            .observe(viewLifecycleOwner) { response ->
                if(response.Sucesso) {
                    exibirSnackBar(response.Mensagem.toString(), RGB(59, 173, 100))
                    navigation.navigate(R.id.action_cadastroFragment_to_loginFragment)
                } else {
                    exibirSnackBar(response.Mensagem.toString(), RGB(223, 40, 63))
                }
            }
    }

    private fun validarInfoUsuario() : Usuario? {
        var nome = binding.inputNomeCadastro.text.toString()
        var email = binding.inputEmailCadastro.text.toString()
        var senha = binding.inputSenhaCadastro.text.toString()

        if(nome.isNullOrBlank() || email.isNullOrBlank() || senha.isNullOrBlank())
            return null

        var usuario = Usuario(nome, email, senha)

        return usuario
    }

    private fun exibirSnackBar(mensagem: String, rgb: RGB) {
        Snackbar.make(binding.root, mensagem, Snackbar.LENGTH_LONG)
            .setBackgroundTint(Color.rgb(rgb.Red, rgb.Green, rgb.Blue))
            .show()
    }
}