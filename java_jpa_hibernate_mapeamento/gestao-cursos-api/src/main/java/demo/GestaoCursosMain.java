package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import javax.persistence.*;
import java.util.*;

public class GestaoCursosMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");

        // Criar instâncias
        Aluno aluno = new Aluno();
        aluno.setNomeCompleto("João Silva");
        aluno.setMatricula("A202401");
        aluno.setNascimento(new GregorianCalendar(1998, Calendar.JULY, 15).getTime());
        aluno.setEmail("joao.silva@email.com");

        Telefone telefone = new Telefone();
        telefone.setDDD("11");
        telefone.setNumero("91234-5678");
        telefone.setAluno(aluno);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua das Flores");
        endereco.setEndereco("Av. Principal");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep(12345678);
        endereco.setAluno(aluno);

        aluno.setTelefones(Arrays.asList(telefone));
        aluno.setEnderecos(Arrays.asList(endereco));

        Professor professor = new Professor();
        professor.setNomeCompleto("Ana Costa");
        professor.setMatricula("P2024001");
        professor.setEmail("ana.costa@educa.com");

        MaterialCurso material = new MaterialCurso();
        material.setUrl("http://material.com/curso-jpa");

        Curso curso = new Curso();
        curso.setNome("Desenvolvimento Java com JPA");
        curso.setSigla("DJPA");
        curso.setProfessor(professor);
        curso.setMaterial(material);
        curso.setAlunos(Arrays.asList(aluno));

        material.setCurso(curso);

        aluno.setCursos(Arrays.asList(curso));

        // Persistir os dados
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(professor);
            em.persist(aluno);
            em.persist(curso);
            em.persist(material);

            em.getTransaction().commit();
            System.out.println("Dados persistidos com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao persistir os dados: " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }

        // Teste usando os models
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        // CRUD exemplo
        Aluno alunoBuscado = alunoModel.findById(aluno.getId());
        System.out.println("Aluno encontrado: " + alunoBuscado.getNomeCompleto());

        List<Curso> cursos = cursoModel.findAll();
        System.out.println("Cursos cadastrados:");
        for (Curso c : cursos) {
            System.out.println("- " + c.getNome());
        }
    }
}