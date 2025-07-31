package com.techcamps.cadastros.demo;

import com.techcamps.cadastros.entities.Produto;
import com.techcamps.cadastros.entities.Pessoa;
import com.techcamps.cadastros.entities.Status;
import com.techcamps.cadastros.models.ProdutoModel;
import com.techcamps.cadastros.models.PessoaModel;

import java.util.Date;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {

        ProdutoModel produtoModel = new ProdutoModel();
        PessoaModel pessoaModel = new PessoaModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(Status.ATIVO); // Enum deve ser criado

        produtoModel.create(p1);

        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Produtos encontrados: " + produtos.size());

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Cristiano Moura");
        pessoa.setEmail("cristiano.moura@email.com");
        pessoa.setCpf("12345678900");
        pessoa.setIdade(30);
        pessoa.setDataNascimento(new Date());

        pessoaModel.create(pessoa);

        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Pessoas encontradas: " + pessoas.size());

        // Teste de update, findById e delete
        Produto produtoLocalizado = produtoModel.findById(p1.getId());
        if (produtoLocalizado != null) {
            produtoLocalizado.setPreco(400.0);
            produtoModel.update(produtoLocalizado);
        }

        pessoaModel.delete(pessoa);
        produtoModel.delete(p1);
    }
}