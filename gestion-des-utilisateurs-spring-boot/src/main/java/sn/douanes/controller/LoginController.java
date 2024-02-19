package sn.douanes.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sn.douanes.exception.entities.EmailExistException;
import sn.douanes.exception.entities.UserNotFoundException;
import sn.douanes.model.Authority;
import sn.douanes.model.Utilisateur;
import sn.douanes.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.apache.commons.lang3.RandomStringUtils;
import sn.douanes.services.EmailService;

import java.sql.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.HttpStatus.OK;
import static sn.douanes.constants.UserImplConstant.EMAIL_ALREADY_EXISTS;
import static sn.douanes.constants.UserImplConstant.NO_USER_FOUND_BY_USERNAME;

@RestController
public class LoginController {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    @PostMapping("/inscription")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public ResponseEntity<String> registerUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur savedUtilisateur = null;
        ResponseEntity response = null;
        try {

            validateNewEmail(EMPTY, utilisateur.getEmail());

            Utilisateur user = new Utilisateur();

            String clearPwd = generatePassword();;
            String hashPwd = passwordEncoder.encode(clearPwd);
            user.setPwd(hashPwd);
            user.setJoinDate(new Date(System.currentTimeMillis()));

            user.setUserId(generateUserId());
            user.setFirstName(utilisateur.getFirstName());
            user.setLastName(utilisateur.getLastName());
            user.setEmail(utilisateur.getEmail());
            user.setMobile(utilisateur.getMobile());

            user.setMatriculeAgent(utilisateur.getMatriculeAgent());
            user.setCodeAgent(utilisateur.getCodeAgent());

            user.setCodeUniteDouaniere(utilisateur.getCodeUniteDouaniere());
            user.setCodeCorpsAgent(utilisateur.getCodeCorpsAgent());
            user.setCodeSection(utilisateur.getCodeSection());

            user.setRole("ROLE_USER");
            // utilisateur.setAuthorities(Role.ROLE_USER.getAuthorities());

            user.setActive(true);
            user.setNotLocked(true);

            savedUtilisateur = utilisateurRepository.save(user);
            if (savedUtilisateur.getUtilisateurId() > 0) {

                LOGGER.info("New user password: " + clearPwd); // à commenter
                emailService.sendNewPasswordEmail(utilisateur.getFirstName(), clearPwd, utilisateur.getEmail());

                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }

    @RequestMapping("/connexion")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public ResponseEntity<Utilisateur> getUserDetailsAfterLogin(Authentication authentication) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(authentication.getName());

        if (null != utilisateur) {
            return new ResponseEntity<>(utilisateur, OK);
        } else {
            return null;
        }
    }

//    @RequestMapping("/connexion")
//    public Utilisateur getUserDetailsAfterLogin(Authentication authentication) {
//        Utilisateur utilisateur = utilisateurRepository.findByEmail(authentication.getName());
//        if (null != utilisateur) {
//            return utilisateur;
//        } else {
//            return null;
//        }
//
//    }


    @GetMapping("/Users")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATEUR')")
    public ResponseEntity<List<Utilisateur>> getAllAjouterUsers() {
        List<Utilisateur> authority = utilisateurRepository.findAll();
        return new ResponseEntity<>(authority, OK);
    }


    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private Utilisateur validateNewEmail(String currentEmail, String newEmail) throws UserNotFoundException, EmailExistException {

        Utilisateur userByNewEmail = utilisateurRepository.findByEmail(newEmail);

        if(StringUtils.isNotBlank(currentEmail)) {

            Utilisateur currentUser = utilisateurRepository.findByEmail(currentEmail);

            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentEmail);
            }
            if(userByNewEmail != null && !currentUser.getUtilisateurId().equals(userByNewEmail.getUtilisateurId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }

            return currentUser;

        } else {

            if(userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }

            return null;
        }
    }

}
