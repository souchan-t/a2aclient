package souchan.a2a.service;

public interface AgentFactory {
    Agent getAgent(String domainUrl, String apiKey);
}
