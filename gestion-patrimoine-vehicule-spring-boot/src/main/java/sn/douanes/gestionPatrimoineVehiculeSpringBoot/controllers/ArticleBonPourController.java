package sn.douanes.gestionPatrimoineVehiculeSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.*;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.ArticleBonPourService;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.BonPourService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
////@RequestMapping(path = { "/", "/user"})
//@RequestMapping( "/")
//@CrossOrigin("http://localhost:4200")
public class ArticleBonPourController {

    @Autowired
    ArticleBonPourService articleBonPourService;

    @Autowired
    BonPourService bonPourService;

    
    @GetMapping("/ArticleBonPours")
    public ResponseEntity<List<ArticleBonPour>> getAllArticleBonPours() {
        List<ArticleBonPour> articleBonPour = articleBonPourService.getAllArticleBonPours();
        return new ResponseEntity<>(articleBonPour, OK);
    }

    @PostMapping("/AjouterArticleBonPour")
    @ResponseBody
    public ArticleBonPour AjouterArticleBonPour(@RequestBody ArticleBonPour articleBonPour) {
        return articleBonPourService.ajouterArticleBonPour(articleBonPour.getIdentifiantBP(), articleBonPour.getCodeArticleBonPour(), articleBonPour.getLibelleArticleBonPour(), articleBonPour.getQuantiteDemandee(), articleBonPour.getCodeTypeObjet(), articleBonPour.getMatriculeAgent());
    }


    @PutMapping("/ModifierArticleBonPour")
    @ResponseBody
    public ArticleBonPour ModifierArticleBonPour(@RequestBody ArticleBonPour a) {
        return articleBonPourService.updateArticleBonPour(a);
    }

    @DeleteMapping("SupprimerArticleBonPourById/{codeArticleBonPour}/{identifiantBP}")
    public void SupprimerArticleBonPour(
            @PathVariable("codeArticleBonPour") String codeArticleBonPour,
            @PathVariable("identifiantBP") BonPour identifiantBP
    ) {
        articleBonPourService.deleteArticleBonPourById(codeArticleBonPour, identifiantBP);
    }

    @GetMapping("RecupererArticleBonPourById/{identifiantBP}/{codeArticleBonPour}")
    public ArticleBonPour RecupererArticleBonPourById(
            @PathVariable("identifiantBP") String identifiantBP,
            @PathVariable("codeArticleBonPour") String codeArticleBonPour
    ) {
        return articleBonPourService.getArticleBonPourById(codeArticleBonPour, bonPourService.getBonPourById(identifiantBP));
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message),
                httpStatus
        );
    }

}
