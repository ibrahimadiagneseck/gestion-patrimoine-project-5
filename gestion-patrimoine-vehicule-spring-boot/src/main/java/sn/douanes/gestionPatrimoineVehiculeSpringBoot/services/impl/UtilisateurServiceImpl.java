package sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.impl;


import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.Utilisateur;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.exception.entities.EmailExistException;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.exception.entities.EmailNotFoundException;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.exception.entities.UserNotFoundException;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.repositories.UtilisateurRepository;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.EmailService;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.LoginAttemptService;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.services.UtilisateurService;


import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static sn.douanes.gestionPatrimoineVehiculeSpringBoot.constants.UserImplConstant.*;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UtilisateurServiceImpl implements UtilisateurService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UtilisateurRepository utilisateurRepository;
    // private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, LoginAttemptService loginAttemptService, EmailService emailService) {
        this.utilisateurRepository = utilisateurRepository;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
    }

    @Override
    public List<Utilisateur> getUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur findUserByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public Utilisateur addNewUser(Utilisateur utilisateur) throws UserNotFoundException, EmailExistException, IOException, MessagingException {

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

        user.setRole(utilisateur.getRole());
        user.setAuthorities(utilisateur.getAuthorities());

        user.setActive(utilisateur.getActive());
        user.setNotLocked(utilisateur.getNotLocked());

        utilisateurRepository.save(user);
        // saveProfileImage(user, profileImage);
        LOGGER.info("New user password: " + clearPwd);
        emailService.sendNewPasswordEmail(user.getFirstName(), clearPwd, user.getEmail());
        return user;
    }

    @Override
    public Utilisateur updateUser(Utilisateur utilisateur) throws UserNotFoundException, EmailExistException, IOException {
        return null;
    }

    @Override
    public void deleteUser(String email) throws IOException {

    }

    @Override
    public void resetPassword(String email) throws MessagingException, EmailNotFoundException {
        Utilisateur user = utilisateurRepository.findByEmail(email);

        if (user == null) {
            throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
        }

        String password = generatePassword();
        user.setPwd(encodePassword(password));

        utilisateurRepository.save(user);
        LOGGER.info("New user password: " + password);
        emailService.sendNewPasswordEmail(user.getFirstName(), password, user.getEmail());
    }


    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
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


