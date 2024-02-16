package sn.douanes.gestionPatrimoineVehiculeSpringBoot.services;

import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.Agent;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.CorpsAgent;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.FonctionAgent;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.UniteDouaniere;

import java.util.List;

public interface AgentService {

    Agent saveAgent(Agent a);
    Agent updateAgent(Agent a);
    void deleteAgent(Agent a);
    void deleteAgentById(String matriculeAgent, CorpsAgent codeCorpsAgent);
    Agent getAgentById(String matriculeAgent, CorpsAgent codeCorpsAgent);
    List<Agent> getAllAgents();

    Agent ajouterAgent(String matriculeAgent, String codeAgent, String nomAgent, String prenomAgent, Integer numeroTelephoneAgent, FonctionAgent fonctionAgent, UniteDouaniere uniteDouaniere, CorpsAgent codeCorpsAgent);
}
