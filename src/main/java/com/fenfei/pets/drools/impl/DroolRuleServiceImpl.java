package com.fenfei.pets.drools.impl;

import com.fenfei.pets.dao.IMotDAO;
import com.fenfei.pets.drools.DroolRuleService;
import com.fenfei.pets.models.CustomTemplate;
import com.fenfei.pets.models.DroolsMessage;
import com.fenfei.pets.models.DroolsRule;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DroolRuleServiceImpl implements DroolRuleService {

    @Autowired
    private IMotDAO motDAO;

    @Override
    public String findRule(String message, Integer status) {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");

        /*
        KieServices kieService = KieServices.Factory.get();
        KieContainer kieContainer = kieService.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        DroolsMessage droolsMessage = new DroolsMessage();
        droolsMessage.setStatus(status);
        droolsMessage.setMessage(message);
        kieSession.insert(droolsMessage);
        kieSession.fireAllRules();
        kieSession.dispose();
        */

        //==================动态过滤规则========================
        String ruleName = "rule_dynamtic";
        String[] conditions = {
                "$message:DroolsMessage(status.equals(DroolsMessage.HELLO) , message : message)"
                , "DroolsMessage(startDate > '2018-08-21' && endDate < '2018-08-25')"
        };
        String rule = parseRuleStr(ruleName , Arrays.asList(conditions));
        System.out.println(rule);

        DroolsMessage droolsMessage1 = new DroolsMessage();
        droolsMessage1.setStatus(0);
        droolsMessage1.setMessage("HelloWorld");
        droolsMessage1.setStartDate(new Date());
        droolsMessage1.setEndDate(new Date());
        buildRules(rule , droolsMessage1);

//        return droolsMessage.getMessage();
        return "";
    }

    @Override
    public CustomTemplate queryById(int id) {
        //这行代码至关重要
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        ArrayList<String> conditions = new ArrayList<>();

        CustomTemplate customTemplate = motDAO.queryTemplateByEventId(id);
        String content = customTemplate.getContent();
        JsonParser jsonParser = new JsonParser();
        JsonArray array = jsonParser.parse(content).getAsJsonArray();
        for (JsonElement element : array) {
            String json = element.getAsJsonObject().toString();
            DroolsRule droolsRule = new Gson().fromJson(json , DroolsRule.class);
            conditions.add(droolsRule.getRule());
            log.info("droolsRule: {}" , droolsRule);
        }

        String rule = parseRuleStr("dynamtic" , conditions);
        System.out.println(rule);
        DroolsMessage droolsMessage1 = new DroolsMessage();
        droolsMessage1.setStatus(0);
        droolsMessage1.setMessage("HelloWorld");
        droolsMessage1.setStartDate(new Date());
        droolsMessage1.setEndDate(new Date());
        buildRules(rule , droolsMessage1);

        return customTemplate;
    }


    //动态加载规则
    private void buildRules(String rule , DroolsMessage droolsMessage) {

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        StatefulKnowledgeSession knowledgeSession = null;
        try {
            knowledgeBuilder.add(ResourceFactory.newByteArrayResource(rule.getBytes("utf-8")), ResourceType.DRL);

            KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }

            KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
            knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());

            knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
            knowledgeSession.insert(droolsMessage);
            knowledgeSession.fireAllRules();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (knowledgeSession != null) {
                knowledgeSession.dispose();
            }
        }
    }

    private String parseRuleStr(String ruleName, List<String> conditions) {
        StringBuilder ruleBuilders = new StringBuilder();

        String packageName = "package com.fenfei.pets\r\n";
        String importDomain = "import com.fenfei.pets.models.DroolsMessage\r\n";
        String rulename = "rule " + ruleName + "\r\n";
        String when = "\t when \n";
        StringBuilder cds = new StringBuilder();
        for (String condition : conditions) {
            cds.append("\t\t").append(condition).append("\r\n");
        }
        String condition = cds.toString();
        String result = "\t then \r\n";
        String execution = "\t\t  System.out.println('hello');\n";
        String end = "end\r\n";

        ruleBuilders.append(packageName)
            .append(importDomain)
            .append(rulename)
            .append(when)
            .append(condition)
            .append(result)
            .append(execution)
            .append(end);

        return ruleBuilders.toString();
    }
}
