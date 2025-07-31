package models;

import entities.Aluno;

import javax.persistence.*;
import java.util.List;

public class AlunoModel {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");

    public void create(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao criar Aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Aluno findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Aluno aluno = null;
        try {
            aluno = em.find(Aluno.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar Aluno: " + e.getMessage());
        } finally {
            em.close();
        }
        return aluno;
    }

    public List<Aluno> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Aluno> alunos = null;
        try {
            alunos = em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar Alunos: " + e.getMessage());
        } finally {
            em.close();
        }
        return alunos;
    }

    public void update(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar Aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            aluno = em.merge(aluno); // Garantir que o objeto esteja gerenciado
            em.remove(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno removido com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao remover Aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}