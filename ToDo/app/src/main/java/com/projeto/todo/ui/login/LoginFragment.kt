package com.projeto.todo.ui.login

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
import com.projeto.todo.databinding.FragmentLoginBinding
import com.projeto.todo.dto.RGB

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navigation: NavController
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        navigation = findNavController()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVoltarLogin.setOnClickListener { navigation.navigate(R.id.action_loginFragment_to_homeFragment) }
        binding.textViewCadastro.setOnClickListener { navigation.navigate(R.id.action_loginFragment_to_cadastroFragment) }
        binding.textViewSenhaEsquecida.setOnClickListener { navigation.navigate(R.id.action_loginFragment_to_recuperacaoSenhaFragment) }
        binding.btnLogin.setOnClickListener {
            var email = binding.inputEmailLogin.text.toString()
            var senha = binding.inputSenhaLogin.text.toString()

            if(validarInfoUsuario(email, senha))
                viewModel.entrarComEmailESenha(email, senha)
            else
                exibirSnackBar("Por favor, preencha as informações corretamente!", RGB(223, 40, 63))
        }

        viewModel.response
            .observe(viewLifecycleOwner) { response ->
                if(response.Sucesso) {
                    navigation.navigate(R.id.action_loginFragment_to_tarefasFragment)
                } else {
                    exibirSnackBar(response.Mensagem.toString(), RGB(223, 40, 63))
                }
            }
    }

    private fun validarInfoUsuario(email: String, senha: String) : Boolean {
        if(email.isNullOrBlank() || senha.isNullOrBlank())
            return false

        return true
    }

    private fun exibirSnackBar(mensagem: String, rgb: RGB) {
        Snackbar.make(binding.root, mensagem, Snackbar.LENGTH_LONG)
            .setBackgroundTint(Color.rgb(rgb.Red, rgb.Green, rgb.Blue))
            .show()
    }
}