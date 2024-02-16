package sn.douanes.gestionPatrimoineVehiculeSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.HttpResponse;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.MarqueVehicule;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.MarqueVehiculeService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
////@RequestMapping(path = { "/", "/user"})
//@RequestMapping( "/")
//@CrossOrigin("http://localhost:4200")
public class MarqueVehiculeController {

    @Autowired
    MarqueVehiculeService marqueVehiculeService;


    @GetMapping("/MarqueVehicules")
    public ResponseEntity<List<MarqueVehicule>> getAllMarqueVehicules() {
        List<MarqueVehicule> marqueVehicule = marqueVehiculeService.getAllMarqueVehicules();
        return new ResponseEntity<>(marqueVehicule, OK);
    }

    @PostMapping("/AjouterMarqueVehicule")
    @ResponseBody
    public MarqueVehicule AjouterMarqueVehicule(@RequestBody MarqueVehicule marqueVehicule) {
        return marqueVehiculeService.saveMarqueVehicule(marqueVehicule);
    }

    @PostMapping("/AjouterRequestParamMarqueVehicule")
    public ResponseEntity<MarqueVehicule> ajouterMarqueVehicule (
            @RequestParam("codeMarque") String codeMarque,
            @RequestParam("libelleMarque") String libelleMarque
    ) {
        MarqueVehicule marqueVehicule = marqueVehiculeService.ajouterMarqueVehicule(codeMarque, libelleMarque);
        return new ResponseEntity<>(marqueVehicule, OK);
    }

    @PutMapping("/ModifierMarqueVehicule")
    @ResponseBody
    public MarqueVehicule ModifierMarqueVehicule(@RequestBody MarqueVehicule t) {
        return marqueVehiculeService.updateMarqueVehicule(t);
    }

    @DeleteMapping("SupprimerMarqueVehiculeById/{id}")
    public void SupprimerMarqueVehiculeById(@PathVariable("id") String codeMarque) {
        marqueVehiculeService.deleteMarqueVehiculeById(codeMarque);
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus
        );
    }


}
