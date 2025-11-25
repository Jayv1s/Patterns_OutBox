package com.patterns;

import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import io.debezium.engine.format.KeyValueHeaderChangeEventFormat;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DatabaseListener {

    private final HandleCDCEvent handleCDCEvent;

    public DatabaseListener(HandleCDCEvent handleCDCEvent) {
        this.handleCDCEvent = handleCDCEvent;
    }

    @PostConstruct
    public void startListening() {
        final Properties props = buildProperties();

        DebeziumEngine<ChangeEvent<String, String>> engine = DebeziumEngine
                .create(KeyValueHeaderChangeEventFormat.of(Json.class, Json.class, Json.class), "io.debezium.embedded.async.ConvertingAsyncEngineBuilderFactory")
                .using(props)
                .notifying(record -> {
                    handleCDCEvent.handle(record.value());
                }).build();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(engine);
    }

    private Properties buildProperties() {
        Properties props =  new Properties();
        props.setProperty("name", "engine");
        props.setProperty("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore");
        props.setProperty("offset.flush.interval.ms", "60000");
        props.setProperty("bootstrap.servers", "localhost:9092");  // Add this line

        /* Required MySQL Connector properties */
        props.setProperty("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        props.setProperty("database.hostname", "localhost");
        props.setProperty("database.port", "3306");
        props.setProperty("database.user", "root");
        props.setProperty("database.password", "rootpassword");
        props.setProperty("database.server.id", "85744");
        props.setProperty("database.server.name", "mysql-server-1");
        props.setProperty("database.include.list", "appdb");
        props.setProperty("table.include.list", "appdb.user_events");

        /* Offset storage configuration */
        props.setProperty("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore");
        // Use a relative filename for offsets to avoid platform-specific /tmp path issues
        // and to make it easier to remove stale offsets during development.
        props.setProperty("offset.storage.file.filename", "offsets.dat");
        props.setProperty("offset.flush.interval.ms", "60000");

        /* Schema history configuration */
        props.setProperty("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory");
        // Use a relative filename for schema history for the same reasons as offsets above.
        props.setProperty("schema.history.internal.file.filename", "schema-history.dat");

        /* Topic configuration */
        props.setProperty("topic.prefix", "outbox");

        return props;
    }
}
