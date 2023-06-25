package com.projeto.todo.ui.tarefas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.projeto.todo.R
import com.projeto.todo.databinding.FragmentTarefasBinding

class TarefasFragment : Fragment() {
    private lateinit var binding: FragmentTarefasBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefas, container, false)
    }
}