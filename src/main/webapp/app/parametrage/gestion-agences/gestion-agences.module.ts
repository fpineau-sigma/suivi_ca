import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AgencesService } from '../../core/service/metier/agences.service';
import { EditerAgenceModalComponent } from './editer-agence/editer-agence-modal.component';
import { ListeAgencesComponent } from './liste-agences/liste-agences.component';
import { gestionAgencesRoute } from './gestion-agences.route';
import { CaClemenceSharedModule } from 'app/shared/shared.module';

@NgModule({
  imports: [RouterModule.forChild(gestionAgencesRoute), CaClemenceSharedModule],
  declarations: [ListeAgencesComponent, EditerAgenceModalComponent],
  entryComponents: [EditerAgenceModalComponent],
  providers: [AgencesService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GestionAgencesModule {}
