## Project Goal:
- [X] Implement Kafka Consumer
- [X] Implement Materialized Aggregation to sum amount
- [ ] Implement API /total_amount
- [ ] Add unit test
- [ ] Add integration test

## Refactor Outbox folder:
- [ ] Remove common modules (like repository module, domain module, etc.)
- [ ] Implement correct layers to the application so they can work isolated
- [ ] Implement docker for each application
- [ ] Update offset logic to not break when Container is recreated

## Refactor Kafka Consumer / Materialized interaction:
- [ ] Create a different database instance for Aggregation tables
- [ ] Move Kafka Consumer `Outbox-Pattern/materialized_aggregation` folder
- [ ] Implement the Idempotency logic with Redis on Kafka_Consumer to ensure a once time consumption of Kafka events
- [ ] Create a Query-Aggregation application to GET aggregation data
- [ ] Adjust Outbox-api to call Query-Aggregation instead of retrieving the data direct from the database


