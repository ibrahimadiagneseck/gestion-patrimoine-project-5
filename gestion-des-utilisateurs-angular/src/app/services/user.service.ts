import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user.model';
import { CustomHttpRespone } from '../model/custom-http-response.model';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  private urlServeur = environment.rooturl1;

  constructor(private httpClient: HttpClient) {}


  // ----------------------------------------------------------------------------
  // RECHERCHER PRESTATAIRES SANS DOUBLONS
  public searchUserListFilterDouble(term: string, listeUsers: User[]): Observable<User[]> {

    if (term.length <= 1) {
      return of([]);
    }

    // Filtrer la liste de Users en fonction du terme de recherche
    const filteredUsers: User[] = listeUsers.filter((users) =>
    users.mobile.toString().includes(term.toLowerCase()) || users.email.toLowerCase().includes(term.toLowerCase())
    );

    // Utilisation de la méthode filter() pour éliminer les doublons
    const filteredUsers1: User[] = filteredUsers.filter((item, index, self) =>
      index === self.findIndex((t) => (
          t.email === item.email || t.mobile === item.mobile
      ))
    );

    return of(filteredUsers1);
  }

  // RECHERCHER User
  public searchUsersList(term: string, listeUsers: User[]): Observable<User[]> {
    if (term.length <= 1) {
      return of([]);
    }

    // Filtrer la liste de Prestataires en fonction du terme de recherche
    const filteredUsers = listeUsers.filter((users) =>
      this.doesUsersMatchTerm(users, term)
    );

    return of(filteredUsers);
  }

  private doesUsersMatchTerm(users: User, term: string): boolean {
    // Vérifier si le terme de recherche correspond à n'importe lequel des attributs du prestataires
    const termLowerCase = term.toLowerCase();
    return (
      users.mobile.toLowerCase().includes(termLowerCase) || users.email.toLowerCase().includes(termLowerCase)
      // Ajoutez d'autres attributs à vérifier si nécessaire
    );
  }
  // ----------------------------------------------------------------------------

  public listeUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.urlServeur}/Users`);
  }

  public ajouterUser(Users: User): Observable<User> {
    return this.httpClient.post<User>(`${this.urlServeur}/AjouterUsers`, Users);
  }

  public modifierUser(formData: FormData): Observable<User> {
    return this.httpClient.post<User>(`${this.urlServeur}/ModifierUsers`, formData);
  }

  public supprimerUserById(ninea: string): Observable<CustomHttpRespone> {
    return this.httpClient.delete<CustomHttpRespone>(`${this.urlServeur}/SupprimerUsersById/${ninea}`);
  }

}
