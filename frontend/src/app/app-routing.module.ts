import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SaveSecretComponent } from './save-secret/save-secret.component';
import { RetrieveSecretComponent } from './retrieve-secret/retrieve-secret.component';

const routes: Routes = [
  { path: '', redirectTo: '/save-secret', pathMatch: 'full' },
  { path: 'save-secret', component:  SaveSecretComponent},
  { path: 'get-secret', component: RetrieveSecretComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
