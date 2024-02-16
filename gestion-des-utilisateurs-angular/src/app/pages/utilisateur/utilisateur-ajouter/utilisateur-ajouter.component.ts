import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Authority } from 'src/app/model/authority.model';
import { User } from 'src/app/model/user.model';
import { Subscription } from 'rxjs';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';
import { AuthorityService } from 'src/app/services/authority.service';
import { SectionsService } from 'src/app/services/sections.service';
import { UniteDouaniere } from 'src/app/model/unite-douaniere.model';
import { UniteDouaniereService } from 'src/app/services/unite-douaniere.service';
import { NotificationType } from 'src/app/enum/notification-type.enum';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Section } from 'jspdf-autotable';
import { Sections } from 'src/app/model/sections.model';
import { CorpsAgent } from 'src/app/model/corps-agent.model';
import { CorpsAgentService } from 'src/app/services/corps-agent.service';

@Component({
  selector: 'app-utilisateur-ajouter',
  // standalone: true,
  // imports: [CommonModule],
  templateUrl: './utilisateur-ajouter.component.html',
  styleUrl: './utilisateur-ajouter.component.css'
})
export class UtilisateurAjouterComponent implements OnInit, OnDestroy {

  public authoritiesSelect: Authority[] = [];

  public uniteDouanieres: UniteDouaniere[] = [];
  public uniteDouaniere: UniteDouaniere = new UniteDouaniere();

  public sections: Sections[] = [];
  public section: Sections = new Sections();

  public authorities: Authority[] = [];
  public authority: Authority = new Authority();

  public corpsAgents: CorpsAgent[] = [];
  public corpsAgent: CorpsAgent = new CorpsAgent();

  public users: User[] = [];
  public user: User = new User();

  private subscriptions: Subscription[] = [];

  constructor(
    public dialogRef: MatDialogRef<UtilisateurAjouterComponent>,
    private authorityService: AuthorityService,
    private sectionsService: SectionsService,
    private uniteDouaniereService: UniteDouaniereService,
    private corpsAgentService: CorpsAgentService,
    private userService: UserService,
    private matDialog: MatDialog,
    private notificationService: NotificationService,
  ) {}

  private sendNotification(type: NotificationType, message: string, titre?: string): void {
    if (message) {
      this.notificationService.showAlert(type, message, titre);
    } else {
      this.notificationService.showAlert(type, 'Une erreur s\'est produite. Veuillez réessayer.', titre);
    }
  }

  ngOnInit(): void {
    this.listeAuthorities();
    this.listeUniteDouanieres();
    this.listeCorpAgents();
    this.listeSections();
  }


  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  // ---------------------------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------------------------
  public listeAuthorities(): void {

    const subscription = this.authorityService.listeAuthorities().subscribe({
      next: (response: Authority[]) => {
        this.authorities = response;
      },
      error: (errorResponse: HttpErrorResponse) => {
        // console.log(errorResponse);
      },
    });

    this.subscriptions.push(subscription);
  }
  // ---------------------------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------------------------


  // ---------------------------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------------------------
  public listeUniteDouanieres(): void {

    const subscription = this.uniteDouaniereService.listeUniteDouanieres().subscribe({
      next: (response: UniteDouaniere[]) => {
        this.uniteDouanieres = response;
      },
      error: (errorResponse: HttpErrorResponse) => {
        // console.log(errorResponse);
      },
    });

    this.subscriptions.push(subscription);
  }
  // ---------------------------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------------------------


  // ---------------------------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------------------------
  public listeCorpAgents(): void {

    const subscription = this.corpsAgentService.listeCorpsAgents().subscribe({
      next: (response: CorpsAgent[]) => {
        this.corpsAgents = response;
      },
      error: (errorResponse: HttpErrorResponse) => {
        // console.log(errorResponse);
      },
    });

    this.subscriptions.push(subscription);
  }
  // ---------------------------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------------------------


  // ---------------------------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------------------------
  public listeSections(): void {

    const subscription = this.sectionsService.listeSections().subscribe({
      next: (response: Sections[]) => {
        this.sections = response;
      },
      error: (errorResponse: HttpErrorResponse) => {
        // console.log(errorResponse);
      },
    });

    this.subscriptions.push(subscription);
  }
  // ---------------------------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------------------------



  popupFermer(): void {
    this.dialogRef.close();
  }


  // --------------------------------------------------------------------------
  private clickButton(buttonId: string): void {
    document.getElementById(buttonId)?.click();
  }

  // pour envoyer tous les formulaires
  public submitForm(): void { 
    this.clickButton('utilisateur-form');
  }


  // --------------------------------------------------------------------------
  // pour executer ajouterBonEntree
  public submitUtilisateurForm(): void { 
    this.clickButton('utilisateur-form')
  }

  public ajouterUtilisateur(utilisateurForm: NgForm): void {

    // SECTEUR ACTIVITE
    utilisateurForm.value.authorities = this.authoritiesSelect;

    console.log(utilisateurForm.value);
    
    this.subscriptions.push(this.userService.ajouterUser(utilisateurForm.value).subscribe({
        next: (response: User) => {
          console.log(response);
          this.popupFermer();
          this.sendNotification(NotificationType.SUCCESS, `Ajout réussi du utilisateur`);
        },
        error: (errorResponse: HttpErrorResponse) => {

        }
      })
    );
  }
  // --------------------------------------------------------------------------


  popupSecteurActivite(authorities: Authority[], user: User, authoritiesSelect?: Authority[]): void {
    // const dialogRef = this.matDialog.open(
    //   PopupSecteurActiviteComponent,
    //   {
    //     width: '80%',
    //     // height: '80%',
    //     enterAnimationDuration: '100ms',
    //     exitAnimationDuration: '100ms',
    //     data:  {
    //       secteurActivites: secteurActivites,
    //       prestataires: prestataires,
    //       secteurActivitesSelected: secteurActivitesSelect
    //     }
    //   }
    // );

    // dialogRef.afterClosed().subscribe(() => {
    //   // ----------------------------------
    //   // Accéder à this.secteurActivitesForm après la fermeture du popup
    //   if (dialogRef.componentInstance instanceof PopupSecteurActiviteComponent) {
    //     this.secteurActivitesSelect = dialogRef.componentInstance.secteurActivitesSelect;
    //     // console.log(this.secteurActivitesSelect);
    //   }
    //   // ----------------------------------
    //   this.ngOnInit();
    // });
  }


  // onSubmit(): void {
  //   // console.log(this.vehiculeForm.value);
  //   // this.AjouterVehicule();
  // }

}
