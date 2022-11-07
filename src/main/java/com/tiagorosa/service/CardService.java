package com.tiagorosa.service;

import com.tiagorosa.model.Card;
import com.tiagorosa.repository.CardRepository;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@ApplicationScoped
public class CardService {

    private final CardRepository repository;

    @Transactional
    public void create(Card entity) {
        repository.create(entity);
    }

    @Transactional
    public Card update(Long id, Card entity) {
        return repository.update(id, entity);
    }

    public List<Card> list(){
        return repository.list();
    }

    @Transactional
    public void delete(Long id){
        repository.delete(id);
    }

}
