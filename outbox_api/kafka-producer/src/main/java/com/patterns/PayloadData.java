package com.patterns;

import java.util.List;

public class PayloadData<V> {
    private Schema schema;
    private Payload payload;

    // Getters and Setters
    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public class Schema {
        private String type;
        private List<Field> fields;
        private boolean optional;
        private String name;
        private int version;

        // Getters and Setters
    }

    public class Field {
        private String type;
        private boolean optional;
        private String name;
        private int version;
        private Parameters parameters;
        private String field;
        private List<Field> fields;

        // Getters and Setters
    }

    public class Parameters {
        private String allowed;

        // Getters and Setters
    }

    public class Payload {
        private V before;
        private V after;
        private Source source;
        private Transaction transaction;
        private String op;
        private Long tsMs;
        private Long tsUs;
        private Long tsNs;

        public V getBefore() {
            return before;
        }

        public void setBefore(V before) {
            this.before = before;
        }

        public V getAfter() {
            return after;
        }

        public void setAfter(V after) {
            this.after = after;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Transaction getTransaction() {
            return transaction;
        }

        public void setTransaction(Transaction transaction) {
            this.transaction = transaction;
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public Long getTsMs() {
            return tsMs;
        }

        public void setTsMs(Long tsMs) {
            this.tsMs = tsMs;
        }

        public Long getTsUs() {
            return tsUs;
        }

        public void setTsUs(Long tsUs) {
            this.tsUs = tsUs;
        }

        public Long getTsNs() {
            return tsNs;
        }

        public void setTsNs(Long tsNs) {
            this.tsNs = tsNs;
        }

        // Getters and Setters
    }

    public class Source {
        private String version;
        private String connector;
        private String name;
        private Long tsMs;
        private String snapshot;
        private String db;
        private String sequence;
        private Long tsUs;
        private Long tsNs;
        private String table;
        private Long serverId;
        private String gtid;
        private String file;
        private Long pos;
        private Integer row;
        private Long thread;
        private String query;

        // Getters and Setters
    }

    public class Transaction {
        private String id;
        private Long totalOrder;
        private Long dataCollectionOrder;

        // Getters and Setters
    }
}

