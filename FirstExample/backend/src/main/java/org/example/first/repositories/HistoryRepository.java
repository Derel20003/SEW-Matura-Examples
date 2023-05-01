package org.example.first.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.example.first.model.History;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HistoryRepository implements PanacheRepository<History> {
}
