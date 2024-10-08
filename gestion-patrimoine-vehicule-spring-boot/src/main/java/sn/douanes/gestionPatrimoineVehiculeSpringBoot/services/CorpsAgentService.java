package sn.douanes.gestionPatrimoineVehiculeSpringBoot.services;

import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.CorpsAgent;

import java.util.List;

public interface CorpsAgentService {

    CorpsAgent saveCorpsAgent(CorpsAgent c);
    CorpsAgent updateCorpsAgent(CorpsAgent c);
    void deleteCorpsAgent(CorpsAgent c);
    void deleteCorpsAgentById(String id);
    CorpsAgent getCorpsAgentById(String id);
    List<CorpsAgent> getAllCorpsAgents();


    CorpsAgent ajouterCorpsAgent(String codeCorpsAgent, String libelleCorpsAgent);

}

