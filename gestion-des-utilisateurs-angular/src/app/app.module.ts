import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpClientXsrfModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { XhrInterceptor } from './interceptors/app.request.interceptor';
import { AuthActivateRouteGuard } from './routeguards/auth.routeguard';
import { DeconnexionComponent } from './pages/deconnexion/deconnexion.component';
import { ConnexionComponent } from './pages/connexion/connexion.component';
import { AccueilComponent } from './pages/accueil/accueil.component';
import { ErreurComponent } from './pages/erreur/erreur.component';
import { ComposantModule } from './composants/composant.module';
import { NgToastModule } from 'ng-angular-popup';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UtilisateurListeComponent } from './pages/utilisateur/utilisateur-liste/utilisateur-liste.component';



export const MY_DATE_FORMATS = {
  parse: {
    dateInput: 'YYYY-MM-DD',
  },
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'YYYY-MM-DD',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@NgModule({
  declarations: [
    AppComponent,
    ConnexionComponent,
    AccueilComponent,
    DeconnexionComponent,
    ErreurComponent,

    UtilisateurListeComponent
  ],
  imports: [
    BrowserModule,
    CommonModule, // ngif ngfor
    FormsModule,
    ReactiveFormsModule, // pour formGroup
    BrowserAnimationsModule,
    HttpClientModule, // pour le backend

    NgToastModule,

    ComposantModule, // composant

    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN',
    }),
  ],
  providers: [
    { provide: MY_DATE_FORMATS, useValue: MY_DATE_FORMATS },
    {
      provide : HTTP_INTERCEPTORS,
      useClass : XhrInterceptor,
      multi : true
    },
    AuthActivateRouteGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
