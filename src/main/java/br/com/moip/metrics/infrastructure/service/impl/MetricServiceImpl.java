package br.com.moip.metrics.infrastructure.service.impl;

import br.com.moip.metrics.domain.model.Ranking;
import br.com.moip.metrics.infrastructure.repository.MetrictRepository;
import br.com.moip.metrics.infrastructure.service.MetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by adercio on 27/04/17.
 */
@Repository
public class MetricServiceImpl implements MetricService {

    private MetrictRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricServiceImpl.class);

    @Autowired
    public MetricServiceImpl(final MetrictRepository repository){
        this.repository = repository;
    }

    @Override
    public Ranking getRanking() {
        long startTime = System.currentTimeMillis();
        Ranking ranking = repository.getRanking();
        long endTime = System.currentTimeMillis();
        LOGGER.info("flow=getRanking;responseTime=" + (endTime - startTime) + " milliseconds");
        return ranking;
    }
}
