package br.com.moip.metrics.infrastructure.repository;

import br.com.moip.metrics.domain.model.Log;

import java.util.List;

/**
 * Created by adercio on 27/04/17.
 */
public interface LogRepository {
    List<Log> findAll();
}
