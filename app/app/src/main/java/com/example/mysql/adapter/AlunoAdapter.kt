package com.example.mysql.adapter


import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysql.databinding.ActivityItemAlunoBinding
import com.example.mysql.model.Aluno

class AlunoAdapter(private val deleteCallback: (Int) -> Unit) : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {
    private var alunos: List<Aluno> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val binding = ActivityItemAlunoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlunoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        val aluno = alunos[position]
        holder.bind(aluno)

        holder.binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Excluir Aluno")
                .setMessage("Deseja realmente excluir o aluno ${aluno.nome}?")
                .setPositiveButton("Sim") { _, _ ->
                    deleteCallback(aluno.id)
                }
                .setNegativeButton("Não", null)
                .show()
        }

        holder.binding.btnEditar.setOnClickListener {
            // Implementar a lógica de edição se necessário
        }
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    fun setData(alunos: List<Aluno>) {
        this.alunos = alunos
        notifyDataSetChanged()
    }

    inner class AlunoViewHolder(val binding: ActivityItemAlunoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(aluno: Aluno) {
            binding.apply {
                nomeTextView.text = aluno.nome
                cpfTextView.text = aluno.cpf
                emailTextView.text = aluno.email
                matriculaTextView.text = aluno.matricula
            }
        }
    }
}




/*
//Código com POST OK
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysql.databinding.ActivityItemAlunoBinding
import com.example.mysql.model.Aluno

class AlunoAdapter : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {
    private var alunos: List<Aluno> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlunoAdapter.AlunoViewHolder {
        val binding = ActivityItemAlunoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlunoViewHolder (binding)
    }

    override fun onBindViewHolder(holder: AlunoAdapter.AlunoViewHolder, position: Int) {
        val aluno = alunos[position]
        holder.bind(aluno)
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    fun setData (alunos: List<Aluno>){
        this.alunos = alunos
        notifyDataSetChanged()
    }

    inner class AlunoViewHolder(private val binding: ActivityItemAlunoBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind (aluno: Aluno){
            binding.apply {
                nomeTextView.text = aluno.nome
                cpfTextView.text = aluno.cpf
                emailTextView.text = aluno.email
                matriculaTextView.text = aluno.matricula
            }
        }
    }

}
*/
