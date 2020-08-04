package it.serp1co.repo.multidatasource.hibernate.secondDs.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Data
@Entity
@NamedNativeQuery(
        name = Example2Entity.EXAMPLE_QUERY,
        resultClass = Example2Entity.class,
        query = "SELECT example.id from EXAMPLE_2_ENTITY where id = :param"
)
public class Example2Entity {
    public static final String EXAMPLE_QUERY = "Example2Entity.ExampleQuery";

    @Id
    private long id;
}
