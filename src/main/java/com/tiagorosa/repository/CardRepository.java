package com.tiagorosa.repository;

import com.tiagorosa.model.Card;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class CardRepository {

    private final EntityManager em;

    public void create(Card entity){
        this.em.persist(entity);
    }

    public Card update(Long id, Card entity){
        entity.setId(id);
        return this.em.merge(entity);
    }

    public List<Card> list(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Card> query = cb.createQuery(Card.class);

        Root<Card> worker= query.from(Card.class);
        query.select(worker);

        TypedQuery<Card> typedQuery = em.createQuery(query);
        return  typedQuery.getResultList();
    }

    public void delete(Long id){
        CriteriaDelete<Card> query = em.getCriteriaBuilder().createCriteriaDelete(Card.class);
        Root<Card> root = query.from(Card.class);
        query.where(root.get("id").in(id));
        em.createQuery(query).executeUpdate();
    }

}
