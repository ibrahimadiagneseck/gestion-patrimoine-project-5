import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CustomHttpRespone } from '../model/custom-http-response.model';
import { BordereauLivraison } from '../model/bordereau-livraison.model';
import { DatePipe } from '@angular/common';
import { MyDate } from '../model/my-date.model';

@Injectable({
  providedIn: 'root'
})
export class BordereauLivraisonService {

  private urlServeur = environment.rooturl2;

  constructor(
    private httpClient: HttpClient
  ) {}


  public listeBordereauLivraisons(): Observable<BordereauLivraison[]> {
    return this.httpClient.get<BordereauLivraison[]>(`${this.urlServeur}/BordereauLivraisons`);
  }

  public ajouterBordereauLivraison(bordereauLivraison: BordereauLivraison): Observable<BordereauLivraison> {
    return this.httpClient.post<BordereauLivraison>(`${this.urlServeur}/AjouterBordereauLivraison`, bordereauLivraison, { withCredentials: true });
  }

  public ajouterBordereauLivraisonRequestParam(formData: FormData): Observable<BordereauLivraison> {
    return this.httpClient.post<BordereauLivraison>(`${this.urlServeur}/AjouterRequestParamBordereauLivraison`, formData, { withCredentials: true });
  }

  public modifierBordereauLivraison(bordereauLivraison: BordereauLivraison): Observable<BordereauLivraison> {
    return this.httpClient.put<BordereauLivraison>(`${this.urlServeur}/ModifierBordereauLivraison`, bordereauLivraison, { withCredentials: true });
  }


  public supprimerBordereauLivraisonById(identifiantBL: string): Observable<CustomHttpRespone> {
    return this.httpClient.delete<CustomHttpRespone>(`${this.urlServeur}/SupprimerBordereauLivraisonById/${identifiantBL}`);
  }


  public createBordereauLivraisonFormData(bordereauLivraison: BordereauLivraison): FormData {

    const formData = new FormData();

    const formattedDate = this.formatterMyDate(bordereauLivraison.dateBL);

    formData.append('numeroBL', bordereauLivraison.numeroBL);
    formData.append('descriptionBL', bordereauLivraison.descriptionBL);
    formData.append('lieuDeLivraison', bordereauLivraison.lieuDeLivraison);
    formData.append('dateBL', formattedDate);
    formData.append('conformiteBL', bordereauLivraison.conformiteBL);
    formData.append('representantPrestataire', bordereauLivraison.representantPrestataire);
    formData.append('codeSection', bordereauLivraison.codeSection.codeSection);
    formData.append('ninea', bordereauLivraison.ninea.ninea);
    formData.append('matriculeAgent', bordereauLivraison.matriculeAgent.matriculeAgent);
    formData.append('codeCorpsAgent', bordereauLivraison.matriculeAgent.codeCorpsAgent.codeCorpsAgent);

    return formData;
  }


  public formatterMyDate(myDate: MyDate): string  {
      if (!myDate || !myDate.year || !myDate.month || !myDate.day) {
          return '';
      }

      const { year, month, day } = myDate;
      const date = new Date(year, month - 1, day);

      const datePipe = new DatePipe('en-US');
      const formattedDate = datePipe.transform(date, 'yyyy-MM-dd') || '';
      return formattedDate;
  }

}
