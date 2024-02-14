package sn.douanes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.douanes.model.CorpsAgent;
import sn.douanes.model.HttpResponse;
import sn.douanes.services.CorpsAgentService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
//@RequestMapping(path = { "/", "/user"})
@RequestMapping( "/")
@CrossOrigin("http://localhost:4200")
public class CorpsAgentController {

    @Autowired
    CorpsAgentService corpsAgentService;

    @GetMapping("/CorpsAgents")
    public ResponseEntity<List<CorpsAgent>> getAllCorpsAgents() {
        List<CorpsAgent> corpsAgent = corpsAgentService.getAllCorpsAgents();
        return new ResponseEntity<>(corpsAgent, OK);
    }

    @PostMapping("/AjouterCorpsAgent")
    @ResponseBody
    public CorpsAgent AjouterCorpsAgent(@RequestBody CorpsAgent corpsAgent) {
        return corpsAgentService.saveCorpsAgent(corpsAgent);
    }

    @PostMapping("/AjouterRequestParamCorpsAgent")
    public ResponseEntity<CorpsAgent> ajouterCorpsAgent (
            @RequestParam("codeCorpsAgent") String codeCorpsAgent,
            @RequestParam("libelleCorpsAgent") String libelleCorpsAgent
    ) {
        CorpsAgent corpsAgent = corpsAgentService.ajouterCorpsAgent(codeCorpsAgent, libelleCorpsAgent);
        return new ResponseEntity<>(corpsAgent, OK);
    }

    @PutMapping("/ModifierCorpsAgent")
    @ResponseBody
    public CorpsAgent ModifierCorpsAgent(@RequestBody CorpsAgent c) {
        return corpsAgentService.updateCorpsAgent(c);
    }

    @DeleteMapping("SupprimerCorpsAgentById/{id}")
    public void SupprimerCorpsAgentById(@PathVariable("id") String codeCorpsAgent) {
        corpsAgentService.deleteCorpsAgentById(codeCorpsAgent);
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus
        );
    }


}
