package br.uece.eesdevops.introducaospringboot;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import java.io.IOException;

class EmbeddedDatabase {

    private final EmbeddedPostgres embeddedPostgres;

    EmbeddedDatabase() throws IOException {
        this.embeddedPostgres = EmbeddedPostgres.builder().setPort(5433).start();
    }

    void stopServer() throws IOException {
        embeddedPostgres.close();
    }

}
