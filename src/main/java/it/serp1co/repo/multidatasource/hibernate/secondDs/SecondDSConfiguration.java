package it.serp1co.repo.multidatasource.hibernate.secondDs;

import it.serp1co.repo.multidatasource.configuration.DataSourceFactory;
import it.serp1co.repo.multidatasource.configuration.PropertiesHolder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

public class SecondDSConfiguration {

    /** DataSource Identifier */
    private static final String DATASOURCE_IDENTIFIER = "ds2";

    private static final String DATASOURCE_CONFIGURATION_PROPERTIES_PREFIX = "datasource." + DATASOURCE_IDENTIFIER;

    /** Qualifiers */
    private static final String DATASOURCE_PROPERTIES_QUALIFIER = DATASOURCE_IDENTIFIER + "DataSourceProperties";
    private static final String DATASOURCE_JPA_PROPERTIES_QUALIFIER = DATASOURCE_IDENTIFIER + "DataSourceJpaProperties";
    public static final String DATASOURCE_QUALIFIER = DATASOURCE_IDENTIFIER + "DataSource";
    public static final String ENTITY_MANAGER_QUALIFIER = DATASOURCE_IDENTIFIER + "EntityManagerFactory";
    public static final String TRANSACTION_MANAGER_QUALIFIER = DATASOURCE_IDENTIFIER + "TransactionManager";

    /** Component-scan base packages for entities and jpa-repositories */
    static final String JPA_REPOSITORIES_BASE_PACKAGE = "it.serp1co.repo.multidatasource.secondDs.repository." + DATASOURCE_IDENTIFIER;
    static final String ENTITIES_BASE_PACKAGE = "it.serp1co.repo.multidatasource.secondDs.entity." + DATASOURCE_IDENTIFIER;

    @Bean(DATASOURCE_PROPERTIES_QUALIFIER)
    @ConfigurationProperties(prefix = DATASOURCE_CONFIGURATION_PROPERTIES_PREFIX)
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(DATASOURCE_JPA_PROPERTIES_QUALIFIER)
    @ConfigurationProperties(prefix = DATASOURCE_CONFIGURATION_PROPERTIES_PREFIX + ".jpa")
    public PropertiesHolder jpaProperties() {
        return new PropertiesHolder();
    }

    @Bean(name = DATASOURCE_QUALIFIER, destroyMethod = "")
    public DataSource dataSource() {
        return DataSourceFactory.create(dataSourceProperties());
    }

    @Bean(ENTITY_MANAGER_QUALIFIER)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource())
                .packages(ENTITIES_BASE_PACKAGE)
                .persistenceUnit(DATASOURCE_IDENTIFIER)
                .properties(jpaProperties().getProperties())
                .build();
    }

    @Bean(TRANSACTION_MANAGER_QUALIFIER)
    public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER_QUALIFIER) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
