package br.uece.eesdevops.introducaospringboot.web.entity;

import br.uece.eesdevops.introducaospringboot.domain.entity.BookLendingStatus;

public class PatchBookLendingStatus {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BookLendingStatus toDomain() {
        return BookLendingStatus.fromString(status);
    }

}
