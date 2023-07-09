package com.projeto.todo.ui.tarefas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.projeto.todo.R
import com.projeto.todo.databinding.FragmentTarefasBinding

class TarefasFragment : Fragment() {
    private lateinit var binding: FragmentTarefasBinding
    private lateinit var navigation: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTarefasBinding.inflate(inflater,  container, false)
        navigation = findNavController()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTelaAdicionarTarefa.setOnClickListener {
            navigation.navigate(R.id.action_tarefasFragment_to_adicionarTarefaFragment)
        }
    }
}