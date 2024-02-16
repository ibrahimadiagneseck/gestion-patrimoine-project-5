package sn.douanes.gestionstockpostgres.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.douanes.gestionstockpostgres.entities.Authority;
import sn.douanes.gestionstockpostgres.entities.HttpResponse;
import sn.douanes.gestionstockpostgres.services.AuthorityService;


import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;


    @GetMapping("/Authorities")
    public ResponseEntity<List<Authority>> getAllAjouterAuthorities() {
        List<Authority> authority = authorityService.getAllAuthorities();
        return new ResponseEntity<>(authority, OK);
    }

    @PostMapping("/AjouterAuthority")
    @ResponseBody
    public Authority AjouterAjouterAuthority(@RequestBody Authority authority) {
        return authorityService.saveAuthority(authority);
    }


    @PutMapping("/ModifierAuthority")
    @ResponseBody
    public Authority ModifierAuthority(@RequestBody Authority t) {

        return authorityService.updateAuthority(t);
    }

    @DeleteMapping("SupprimerAuthorityById/{id}")
    public void SupprimerAuthorityById(@PathVariable("id") String authorityId) {
        authorityService.deleteAuthorityById(authorityId);
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus
        );
    }


}
