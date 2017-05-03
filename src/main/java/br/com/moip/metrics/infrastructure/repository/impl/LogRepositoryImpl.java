package br.com.moip.metrics.infrastructure.repository.impl;

import br.com.moip.metrics.domain.model.Log;
import br.com.moip.metrics.infrastructure.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by adercio on 27/04/17.
 */
@Repository
public class LogRepositoryImpl implements LogRepository{

    private static final Pattern PATTERN_URI = Pattern.compile("request_to=\\\"(\"(?:\\\\.|[^\"\\\\]+)*\"|(?:\\\\.|[^,\"\\\\]+)*)\\\"");
    private static final Pattern PATTERN_STATUS = Pattern.compile("response_status=\\\"(\"(?:\\\\.|[^\"\\\\]+)*\"|(?:\\\\.|[^,\"\\\\]+)*)\\\"");
    private static final Logger LOGGER = LoggerFactory.getLogger(LogRepositoryImpl.class);
    private List<Log> logs = new ArrayList<>();

    @Value("${location.file}")
    private String LOCATION_FILE;

    @PostConstruct
    public void init(){
        try (Stream<String> stream = Files.lines(Paths.get(LOCATION_FILE))) {
            stream.forEach( line ->{
                Matcher matcherUri = PATTERN_URI.matcher(line);
                Matcher matcheStatus = PATTERN_STATUS.matcher(line);

                while(matcherUri.find() && matcheStatus.find()){
                    if(matcherUri.group(1) != null && matcheStatus.group(1) !=null) {
                        Log log = new Log();
                        log.setRequestTo(matcherUri.group(1));
                        log.setResponseStatus(Integer.parseInt(matcheStatus.group(1)));
                        logs.add(log);
                    }

                }
            });
        } catch (IOException e) {
            LOGGER.error("flow=findAll;proccess=loadFileLog;error=" + e.getMessage(), e);
        }
    }

    @Override
    public List<Log> findAll() {
        if(this.logs.isEmpty()){
            init();
        }
        return logs;
    }
}
