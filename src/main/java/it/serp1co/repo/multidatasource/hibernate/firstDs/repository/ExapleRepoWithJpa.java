package it.serp1co.repo.multidatasource.hibernate.firstDs.repository;


import it.serp1co.repo.multidatasource.hibernate.firstDs.entity.Example1Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExapleRepoWithJpa extends JpaRepository<Example1Entity, Long> {

    //entity manager takes up configuration automatically using jpa based on package scan

}
