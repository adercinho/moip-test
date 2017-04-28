package br.com.moip.metrics.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Ranking {

    private List<Access> accessList = new ArrayList<>();
    private List<Webhook> webhookList = new ArrayList<>();

    public Ranking(List<Log> logs) {
        logs.forEach(log -> {
            addRankingAccess(new Access(log.getRequestTo()));
            addRankingWebhook(new Webhook(log.getResponse_status()));
        });

        Collections.sort(webhookList);
        Collections.sort(accessList);
    }

    private void addRankingAccess(Access newAccess){
        if(accessList.isEmpty()){
            accessList.add(newAccess);
            return ;
        }
        boolean found = false;
        for(int i =0; i < accessList.size(); i++){
            Access access = accessList.get(i);
            if(access.getUrl().equals(newAccess.getUrl())){
                accessList.get(i).addInscrementCount();
                found = true;
            }
        }
        if(!found){
            accessList.add(newAccess);
        }
    }

    private void addRankingWebhook(Webhook newWebhook){
        if(webhookList.isEmpty()){
            webhookList.add(newWebhook);
            return ;
        }
        boolean found = false;
        for(int i =0; i < webhookList.size(); i++){
            Webhook webhook = webhookList.get(i);
            if(webhook.getHttpStatus() == newWebhook.getHttpStatus()){
                webhookList.get(i).addInscrementCount();
                found = true;
            }
        }
        if(!found){
            webhookList.add(newWebhook);
        }
    }

    public List<Access> getAccessList() {
        return accessList;
    }

    public void setAccessList(List<Access> accessList) {
        this.accessList = accessList;
    }

    public List<Webhook> getWebhookList() {
        return webhookList;
    }

    public void setWebhookList(List<Webhook> webhookList) {
        this.webhookList = webhookList;
    }
}
