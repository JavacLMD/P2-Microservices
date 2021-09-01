package com.github.balashov.ItemInfoService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@EnableReactiveCassandraRepositories
@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace = "project_two";

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints = "localhost";

    @Value("${spring.data.cassandra.schema-action}")
    private SchemaAction schemaAction = SchemaAction.CREATE_IF_NOT_EXISTS;

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return schemaAction;
    }
}
