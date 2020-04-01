import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ExercicesService } from 'app/core/service/metier/exercices.service';
import { EditerExerciceModalComponent } from './editer-exercice/editer-exercice-modal.component';
import { ListeExercicesComponent } from './liste-exercices/liste-exercices.component';
import { gestionExercicesRoute } from './gestion-exercices.route';
import { CaClemenceSharedModule } from 'app/shared/shared.module';

@NgModule({
  imports: [RouterModule.forChild(gestionExercicesRoute), CaClemenceSharedModule],
  declarations: [ListeExercicesComponent, EditerExerciceModalComponent],
  entryComponents: [EditerExerciceModalComponent],
  providers: [ExercicesService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GestionExercicesModule {}
