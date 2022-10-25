package com.asr.personal.inventory.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableReactiveMongoRepositories(basePackages = { "com.asr.personal.inventory.repository" })
public class DatabaseConfiguration extends AbstractReactiveMongoConfiguration {

  DatabaseProperties properties;

  @Override
  protected String getDatabaseName() {
    return properties.getInventoryDatabaseName();
  }
}
