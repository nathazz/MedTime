package com.example.medtime.model.dto


class Medicamento(
    private var id : Int?,
    private var nome : String,
    private var imagem : Byte
){



    fun getId() : Int?{
        return id
    }

    fun getNome() : String{
        return nome
    }

    fun setNome(nome : String){
        this.nome = nome
    }

    fun getImagem() : Byte{
        return imagem
    }

    fun setImagem(imagem : Byte){
        this.imagem = imagem
    }

    override fun toString(): String {
        return "id: $id\nnome: $nome\nimagem: $imagem\n"
    }
}