package br.com.moip.metrics.view.endpoint;

import br.com.moip.metrics.domain.model.Ranking;
import br.com.moip.metrics.infrastructure.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/metrics/")
public class MetricEndpoint {

    private MetricService service;

    @Autowired
    public MetricEndpoint(MetricService service){
        this.service = service;
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public Ranking getRanking() {
        return service.getRanking();
    }
}
