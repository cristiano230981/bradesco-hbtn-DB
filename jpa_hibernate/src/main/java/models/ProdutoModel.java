package com.techcamps.cadastros.models;

import com.techcamps.cadastros.entities.Produto;

import javax.persistence.*;
import java.util.*;

public class ProdutoModel {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");

    public void create(Produto p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao criar produto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void update(Produto p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Produto p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, p.getId());
            if (produto != null) em.remove(produto);
            em.getTransaction().commit();
            System.out.println("Produto excluído com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Produto findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public List<Produto> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        } finally {
            em.close();
        }
    }
}
