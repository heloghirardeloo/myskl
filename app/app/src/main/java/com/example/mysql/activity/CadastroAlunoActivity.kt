package com.example.mysql.activity


import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mysql.api.EnderecoAPI
import com.example.mysql.api.RetrofitHelper
import com.example.mysql.databinding.ActivityCadastroAlunoBinding
import com.example.mysql.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroAlunoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroAlunoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val cpf = binding.edtCpf.text.toString()
            val email = binding.edtEmail.text.toString()
            val matricula = binding.edtMatricula.text.toString()

            if (nome.isNotEmpty() && cpf.isNotEmpty() && email.isNotEmpty() && matricula.isNotEmpty()) {
                val aluno = Aluno(0, nome, cpf, email, matricula) // id is 0 for new records
                salvarAluno(aluno)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarAluno(aluno: Aluno) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI::class.java)
        val call = service.inserirAluno(aluno)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK)
                    finish() // Return to MainActivity and trigger an update
                } else {
                    Toast.makeText(this@CadastroAlunoActivity, "Erro ao salvar aluno.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastroAlunoActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}



/*//Código com POST OK
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mysql.api.EnderecoAPI
import com.example.mysql.api.RetrofitHelper
import com.example.mysql.databinding.ActivityCadastroAlunoBinding
import com.example.mysql.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroAlunoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroAlunoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val cpf = binding.edtCpf.text.toString()
            val email = binding.edtEmail.text.toString()
            val matricula = binding.edtMatricula.text.toString()

            val aluno = Aluno( nome, cpf, email, matricula)

            val retrofit = RetrofitHelper.getRetrofitInstance()
            val service = retrofit.create(EnderecoAPI::class.java)

            val call = service.inserirAluno(aluno)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Aluno inserido com sucesso
                        val intent = Intent()
                        setResult(RESULT_OK, intent)
                        finish()
                    } else {
                        // Falha ao inserir aluno
                        // Exibir mensagem de erro, se necessário
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Falha na chamada da API
                    // Exibir mensagem de erro, se necessário
                }
            })
        }
    }
}*/
