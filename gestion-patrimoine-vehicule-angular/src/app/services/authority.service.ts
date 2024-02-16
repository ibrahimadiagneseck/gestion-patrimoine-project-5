import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CustomHttpRespone } from '../model/custom-http-response.model';
import { Authority } from '../model/authority.model';



@Injectable({
  providedIn: 'root'
})
export class AuthorityService {

  private urlServeur = environment.rooturl1;

  constructor(private httpClient: HttpClient) {}

  
  public listeAuthorities(): Observable<Authority[]> {
    return this.httpClient.get<Authority[]>(`${this.urlServeur}/Authorities`);
  }

  public ajouterAuthority(authority: Authority): Observable<Authority> {
    return this.httpClient.post<Authority>(`${this.urlServeur}/AjouterAuthority`, authority);
  }

  public modifierAuthority(authority: Authority): Observable<Authority> {
    return this.httpClient.post<Authority>(`${this.urlServeur}/ModifierAuthority`, authority);
  }

  public supprimerAuthorityById(authorityId: string): Observable<CustomHttpRespone> {
    return this.httpClient.delete<CustomHttpRespone>(`${this.urlServeur}/SupprimerAuthorityById/${authorityId}`);
  }

}
