package br.com.moip.metrics.infrastructure.repository.impl;

import br.com.moip.metrics.domain.model.Log;
import br.com.moip.metrics.domain.model.Ranking;
import br.com.moip.metrics.infrastructure.repository.LogRepository;
import br.com.moip.metrics.infrastructure.repository.MetrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by adercio on 27/04/17.
 */
@Repository
public class MetricRepositoryImpl implements MetrictRepository{

    @Autowired
    private LogRepository logRepository;

    @Override
    public Ranking getRanking() {
        List<Log> logs = logRepository.findAll();
        return new Ranking(logs);
    }
}
