package com.ctt.heroismarvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ctt.heroismarvel.model.Bonecos
import com.ctt.heroismarvel.service.BonecosService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var campoPersonagem: EditText
    private lateinit var botaoPesquisar: Button
    private lateinit var respostaImagem: TextView
    private lateinit var respostaNome: TextView
    private lateinit var respostaDescricao: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        campoPersonagem = findViewById(R.id.edtPersonagem)
        botaoPesquisar = findViewById(R.id.btnPesquisar)

        botaoPesquisar.setOnClickListener {
            val personagem = campoPersonagem.text.toString()
            if (personagem.isNotEmpty()) {
                buscarPersonagem(personagem)
            } else {
                campoPersonagem.error = "Nome incorreto"

            }
        }

    }

    fun buscarPersonagem(nomeBonecos: String) {
        try {
            val retrofitClient = Network.RetrofitConfig("http://gateway.marvel.com/")
            val servico = retrofitClient.create(BonecosService::class.java)
            val chamada = servico.buscarBonecos("730a711ecb0c59f33c187660fd6a03be","a3ac66922202f6f9d788f787cb46df01","2000",nomeBonecos)

            chamada.enqueue(
                object : Callback<Bonecos> {
                    override fun onResponse(call: Call<Bonecos>, response: Response<Bonecos>) {
                        val boneco = response.body()
                        boneco?.let {
                            if (boneco != null) {
                                respostaNome.text = it.nome
                                respostaDescricao.text = it.descricao
                            } else {
                                respostaNome.text = "Ops... Herói não encontrado :("
                                respostaDescricao.text = "Tente pesquisar em inglês"
                            }
                        }
                    }

                    override fun onFailure(call: Call<Bonecos>, t: Throwable) {
                        respostaNome.text = t.cause.toString()
                        respostaDescricao.text = t.message.toString()
                    }

                }
            )
        } catch (e:Exception) {
            respostaNome.text = e.toString()

        }
    }
}