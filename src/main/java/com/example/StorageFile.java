package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class StorageFile extends PanacheEntity {

    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    public byte[] data;
}
