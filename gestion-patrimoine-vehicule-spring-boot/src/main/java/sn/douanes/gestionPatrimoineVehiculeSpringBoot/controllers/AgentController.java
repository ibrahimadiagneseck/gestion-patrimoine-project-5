package sn.douanes.gestionPatrimoineVehiculeSpringBoot.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.*;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.AgentService;

import static org.springframework.http.HttpStatus.OK;


@RestController
////@RequestMapping(path = { "/", "/user"})
//@RequestMapping( "/")
//@CrossOrigin("http://localhost:4200")
public class AgentController {

    @Autowired
    AgentService agentService;


    @GetMapping("/Agents")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public ResponseEntity<List<Agent>> getAllAgents() {
        List<Agent> agents = agentService.getAllAgents();
        return new ResponseEntity<>(agents, OK);
    }

    @PostMapping("/AjouterAgent")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public Agent AjouterAgent(@RequestBody Agent agent) {
        return agentService.saveAgent(agent);
    }

    @PostMapping("/AjouterRequestParamAgent")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public ResponseEntity<Agent> ajouterAgent (
            @RequestParam("matriculeAgent") String matriculeAgent,
            @RequestParam("codeAgent") String codeAgent,
            @RequestParam("nomAgent") String nomAgent,
            @RequestParam("prenomAgent") String prenomAgent,
            @RequestParam("numeroTelephoneAgent") Integer numeroTelephoneAgent,
            @RequestParam("codeFonctionAgent") FonctionAgent codeFonctionAgent,
            @RequestParam("codeUniteDouaniere") UniteDouaniere codeUniteDouaniere,
            @RequestParam("codeCorpsAgent") CorpsAgent codeCorpsAgent
    ) {
        Agent agent = agentService.ajouterAgent(matriculeAgent, codeAgent, nomAgent, prenomAgent, numeroTelephoneAgent, codeFonctionAgent, codeUniteDouaniere, codeCorpsAgent);
        return new ResponseEntity<>(agent, OK);
    }

    @PutMapping("/ModifierAgent")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public Agent ModifierAgent(@RequestBody Agent a) {
        return agentService.updateAgent(a);
    }

    @DeleteMapping("SupprimerAgentById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public void SupprimerAgentById(
            @PathVariable("matriculeAgent") String matriculeAgent,
            @PathVariable("codeCorpsAgent") CorpsAgent codeCorpsAgent
    ) {
        agentService.deleteAgentById(matriculeAgent, codeCorpsAgent);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus
        );
    }

}
