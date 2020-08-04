package it.serp1co.repo.multidatasource.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

public class DataSourceFactory {

    private DataSourceFactory() {
    }

    public static DataSource create(DataSourceProperties dataSourceProperties) {
        if (StringUtils.hasText(dataSourceProperties.getJndiName())) {
            JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
            return dataSourceLookup.getDataSource(dataSourceProperties.getJndiName());
        }
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}


