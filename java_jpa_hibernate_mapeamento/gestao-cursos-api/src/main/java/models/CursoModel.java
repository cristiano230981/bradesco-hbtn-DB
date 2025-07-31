package models;

import entities.Curso;

import javax.persistence.*;
import java.util.List;

public class CursoModel {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");

    public void create(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao criar Curso: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Curso findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Curso curso = null;
        try {
            curso = em.find(Curso.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar Curso: " + e.getMessage());
        } finally {
            em.close();
        }
        return curso;
    }

    public List<Curso> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = null;
        try {
            cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar Cursos: " + e.getMessage());
        } finally {
            em.close();
        }
        return cursos;
    }

    public void update(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar Curso: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            curso = em.merge(curso); // Garantir que esteja gerenciado
            em.remove(curso);
            em.getTransaction().commit();
            System.out.println("Curso removido com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao remover Curso: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}