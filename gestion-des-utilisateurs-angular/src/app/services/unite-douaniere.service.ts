import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';



import { environment } from 'src/environments/environment';
import { CustomHttpRespone } from '../model/custom-http-response.model';
import { UniteDouaniere } from '../model/unite-douaniere.model';

@Injectable({
  providedIn: 'root'
})
export class UniteDouaniereService {

  private urlServeur = environment.rooturl2;

  constructor(private httpClient: HttpClient) { }


  public listeUniteDouanieres(): Observable<UniteDouaniere[]> {
    return this.httpClient.get<UniteDouaniere[]>(`${this.urlServeur}/UniteDouanieres`);
  }

  public ajouterUniteDouaniere(formData: FormData): Observable<UniteDouaniere> {
    return this.httpClient.post<UniteDouaniere>(`${this.urlServeur}/AjouterUniteDouaniere`, formData);
  }

  public modifierUniteDouaniere(formData: FormData): Observable<UniteDouaniere> {
    return this.httpClient.put<UniteDouaniere>(`${this.urlServeur}/ModifierUniteDouaniere`, formData);
  }

  public supprimerUniteDouaniere(codeUniteDouaniere: string): Observable<CustomHttpRespone> {
    return this.httpClient.delete<CustomHttpRespone>(`${this.urlServeur}/SupprimerUniteDouaniereByUniteDouaniereId/${codeUniteDouaniere}`);
  }

  public recupererUniteDouaniereById(codeUniteDouaniere: string): Observable<UniteDouaniere> {
    return this.httpClient.get<UniteDouaniere>(`${this.urlServeur}/RecupererUniteDouaniereById/${codeUniteDouaniere}`);
  }

}
