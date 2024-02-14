package sn.douanes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.douanes.model.HttpResponse;
import sn.douanes.model.UniteDouaniere;
import sn.douanes.services.UniteDouaniereService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
//@RequestMapping(path = { "/", "/user"})
@RequestMapping( "/")
@CrossOrigin("http://localhost:4200")
public class UniteDouaniereController {

    @Autowired
    UniteDouaniereService uniteDouaniereService;


    @GetMapping("/UniteDouanieres")
    public ResponseEntity<List<UniteDouaniere>> getAllUniteDouanieres() {
        List<UniteDouaniere> uniteDouaniere = uniteDouaniereService.getAllUniteDouanieres();
        return new ResponseEntity<>(uniteDouaniere, OK);
    }

    @PostMapping("/AjouterUniteDouaniere")
    @ResponseBody
    public UniteDouaniere AjouterUniteDouaniere(@RequestBody UniteDouaniere uniteDouaniere) {
        return uniteDouaniereService.saveUniteDouaniere(uniteDouaniere);
    }

//    @PostMapping("/AjouterRequestParamUniteDouaniere")
//    public ResponseEntity<UniteDouaniere> ajouterUniteDouaniere (
//            @RequestParam("codeUniteDouaniere") String codeUniteDouaniere,
//            @RequestParam("nomUniteDouaniere") String nomUniteDouaniere,
//            @RequestParam("effectifUniteDouaniere") Integer effectifUniteDouaniere,
//            @RequestParam("nombreArme") Integer nombreArme,
//            @RequestParam("nombreAutomobile") Integer nombreAutomobile,
//            @RequestParam("nombreMateriel") Integer nombreMateriel,
//            @RequestParam("codeTypeUniteDouaniere") TypeUniteDouaniere codeTypeUniteDouaniere
//    ) {
//        UniteDouaniere uniteDouaniere = uniteDouaniereService.ajouterUniteDouaniere(codeUniteDouaniere, nomUniteDouaniere, effectifUniteDouaniere, nombreArme, nombreAutomobile,  nombreMateriel, codeTypeUniteDouaniere);
//        return new ResponseEntity<>(uniteDouaniere, OK);
//    }

    @PutMapping("/ModifierUniteDouaniere")
    @ResponseBody
    public UniteDouaniere ModifierUniteDouaniere(@RequestBody UniteDouaniere u) {

        return uniteDouaniereService.updateUniteDouaniere(u);
    }

    @DeleteMapping("SupprimerUniteDouaniereById/{id}")
    public void SupprimerUniteDouaniereById(@PathVariable("id") String codeUniteDouaniere) {
        uniteDouaniereService.deleteUniteDouaniereById(codeUniteDouaniere);
    }

    @GetMapping("RecupererUniteDouaniereById/{id}")
    public UniteDouaniere RecupererUniteDouaniereById(@PathVariable("id") String codeUniteDouaniere) {
        return uniteDouaniereService.getUniteDouaniereById(codeUniteDouaniere);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus
        );
    }


}
