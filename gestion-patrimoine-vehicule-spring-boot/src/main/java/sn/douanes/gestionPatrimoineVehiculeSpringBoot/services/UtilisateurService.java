package sn.douanes.gestionPatrimoineVehiculeSpringBoot.services;

import sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities.Utilisateur;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.exception.entities.EmailExistException;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.exception.entities.EmailNotFoundException;
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.exception.entities.UserNotFoundException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UtilisateurService {

    // Utilisateur register(Utilisateur utilisateur) throws UserNotFoundException, EmailExistException, MessagingException;

    List<Utilisateur> getUsers();


    Utilisateur findUserByEmail(String email);

    Utilisateur addNewUser(Utilisateur utilisateur) throws UserNotFoundException, EmailExistException, IOException, MessagingException;

    Utilisateur updateUser(Utilisateur utilisateur) throws UserNotFoundException, EmailExistException, IOException;

    void deleteUser(String email) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    // User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

}
