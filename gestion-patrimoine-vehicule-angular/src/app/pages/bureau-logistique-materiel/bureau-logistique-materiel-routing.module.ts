import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReceptionVehiculeListeComponent } from './vehicules/reception/reception-vehicule-liste/reception-vehicule-liste.component';
import { ConsultationVehiculeListeComponent } from './vehicules/consultation/consultation-vehicule-liste/consultation-vehicule-liste.component';
import { ReceptionVehiculeDetailComponent } from './vehicules/reception/reception-vehicule-detail/reception-vehicule-detail.component';
import { ConsultationReceptionVehiculeListeComponent } from './vehicules/consultation/consultation-reception-vehicule-liste/consultation-reception-vehicule-liste.component';
import { ConsultationReceptionVehiculeDetailComponent } from './vehicules/consultation/consultation-reception-vehicule-detail/consultation-reception-vehicule-detail.component';
import { DotationVehiculeListeComponent } from './vehicules/dotation/dotation-vehicule-liste/dotation-vehicule-liste.component';
import { DotationVehiculeDetailComponent } from './vehicules/dotation/dotation-vehicule-detail/dotation-vehicule-detail.component';
import { DotationVehiculeAjouterBonSortieComponent } from './vehicules/dotation/dotation-vehicule-ajouter-bon-sortie/dotation-vehicule-ajouter-bon-sortie.component';
import { DotationVehiculeDetailBonSortieDetailComponent } from './vehicules/dotation/dotation-vehicule-detail-bon-sortie-detail/dotation-vehicule-detail-bon-sortie-detail.component';
import { AuthActivateRouteGuard } from 'src/app/routeguards/auth.routeguard';

const routes: Routes = [

  { path: 'consultation-vehicule', component: ConsultationVehiculeListeComponent, canActivate: [AuthActivateRouteGuard]},

  { path: 'reception-vehicule', component: ReceptionVehiculeListeComponent, canActivate: [AuthActivateRouteGuard]},

  { path: 'consultation-reception-vehicule', component: ConsultationReceptionVehiculeListeComponent, canActivate: [AuthActivateRouteGuard]},

  { path: 'reception-vehicule-detail/:idIdentifiantBE', component: ReceptionVehiculeDetailComponent, canActivate: [AuthActivateRouteGuard]},

  { path: 'consultation-reception-vehicule-detail/:idIdentifiantBE', component: ConsultationReceptionVehiculeDetailComponent, canActivate: [AuthActivateRouteGuard]},


  { path: 'affectation-vehicule', component: DotationVehiculeListeComponent, canActivate: [AuthActivateRouteGuard]},
  { path: 'dotation-vehicule-detail/:identifiantBP/:codeArticleBonPour', component: DotationVehiculeDetailComponent, canActivate: [AuthActivateRouteGuard], data: {
    roles: ['USER']
  }},
  { path: 'dotation-vehicule-ajouter-bon-sortie', component: DotationVehiculeAjouterBonSortieComponent, canActivate: [AuthActivateRouteGuard], data: {
    roles: ['USER']
  }},
  { path: 'dotation-vehicule-detail-bon-sortie-detail/:idIdentifiantBS', component: DotationVehiculeDetailBonSortieDetailComponent, canActivate: [AuthActivateRouteGuard]},


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BureauLogistiqueMaterielRoutingModule { }
