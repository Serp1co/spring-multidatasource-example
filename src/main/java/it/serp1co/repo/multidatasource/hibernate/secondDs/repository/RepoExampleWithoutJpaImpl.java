package it.serp1co.repo.multidatasource.hibernate.secondDs.repository;

import it.serp1co.repo.multidatasource.hibernate.secondDs.SecondDSConfiguration;
import it.serp1co.repo.multidatasource.hibernate.secondDs.entity.Example2Entity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class RepoExampleWithoutJpaImpl implements RepoExampleWithoutJpa {

    public RepoExampleWithoutJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Entity manager qualifier needs to be specified for the entity manager to get it
    @Qualifier(SecondDSConfiguration.ENTITY_MANAGER_QUALIFIER)
    private EntityManager entityManager;

    @Override
    public Map<Long, Example2Entity> doExampleQuery(String param) {
        return entityManager.createNamedQuery(Example2Entity.EXAMPLE_QUERY, Example2Entity.class)
                .setParameter("param", param)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(Example2Entity::getId, Function.identity()));
    }
}