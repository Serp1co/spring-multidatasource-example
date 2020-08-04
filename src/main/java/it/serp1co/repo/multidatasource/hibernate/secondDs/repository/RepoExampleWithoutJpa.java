package it.serp1co.repo.multidatasource.hibernate.secondDs.repository;

import it.serp1co.repo.multidatasource.hibernate.secondDs.entity.Example2Entity;

import java.util.Map;

public interface RepoExampleWithoutJpa {
    public Map<Long, Example2Entity> doExampleQuery(String param);
}
