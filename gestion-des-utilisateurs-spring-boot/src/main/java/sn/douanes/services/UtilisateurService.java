package sn.douanes.services;

import org.springframework.web.multipart.MultipartFile;
import sn.douanes.exception.entities.*;
import sn.douanes.model.Utilisateur;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UtilisateurService {

    // Utilisateur register(Utilisateur utilisateur) throws UserNotFoundException, EmailExistException, MessagingException;

    List<Utilisateur> getUsers();


    Utilisateur findUserByEmail(String email);

    Utilisateur addNewUser(Utilisateur utilisateur) throws UserNotFoundException, EmailExistException, IOException, NotAnImageFileException, MessagingException;

    Utilisateur updateUser(Utilisateur utilisateur) throws UserNotFoundException, EmailExistException, IOException, NotAnImageFileException;

    void deleteUser(String email) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    // User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

}
