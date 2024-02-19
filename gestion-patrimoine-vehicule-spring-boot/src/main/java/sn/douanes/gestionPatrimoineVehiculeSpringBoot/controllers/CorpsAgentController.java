package sn.douanes.gestionPatrimoineVehiculeSpringBoot.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.CorpsAgent;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.HttpResponse;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.CorpsAgentService;

import static org.springframework.http.HttpStatus.OK;


@RestController
////@RequestMapping(path = { "/", "/user"})
//@RequestMapping( "/")
//@CrossOrigin("*")
public class CorpsAgentController {

    @Autowired
    CorpsAgentService corpsAgentService;

    @GetMapping("/CorpsAgents")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public ResponseEntity<List<CorpsAgent>> getAllCorpsAgents() {
        List<CorpsAgent> corpsAgent = corpsAgentService.getAllCorpsAgents();
        return new ResponseEntity<>(corpsAgent, OK);
    }

    @PostMapping("/AjouterCorpsAgent")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public CorpsAgent AjouterCorpsAgent(@RequestBody CorpsAgent corpsAgent) {
        return corpsAgentService.saveCorpsAgent(corpsAgent);
    }

    @PostMapping("/AjouterRequestParamCorpsAgent")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public ResponseEntity<CorpsAgent> ajouterCorpsAgent (
            @RequestParam("codeCorpsAgent") String codeCorpsAgent,
            @RequestParam("libelleCorpsAgent") String libelleCorpsAgent
    ) {
        CorpsAgent corpsAgent = corpsAgentService.ajouterCorpsAgent(codeCorpsAgent, libelleCorpsAgent);
        return new ResponseEntity<>(corpsAgent, OK);
    }

    @PutMapping("/ModifierCorpsAgent")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public CorpsAgent ModifierCorpsAgent(@RequestBody CorpsAgent c) {
        return corpsAgentService.updateCorpsAgent(c);
    }

    @DeleteMapping("SupprimerCorpsAgentById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public void SupprimerCorpsAgentById(@PathVariable("id") String codeCorpsAgent) {
        corpsAgentService.deleteCorpsAgentById(codeCorpsAgent);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus
        );
    }

}
