package com.artefatox.spring_jpa_read_replica.configuration;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.slf4j.MDC;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class RountingDataSource extends AbstractRoutingDataSource {

    @Override
    protected @Nullable Object determineCurrentLookupKey() {
        DataSourceType type = TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? DataSourceType.REPLICA : DataSourceType.MASTER;
        log.info("Selecionado instancia: {}", type);
        MDC.put("db", type.name());
        return type;
    }
}
