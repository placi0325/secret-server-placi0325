import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SaveSecretComponent } from './save-secret/save-secret.component';
import { RetrieveSecretComponent } from './retrieve-secret/retrieve-secret.component';

@NgModule({
  declarations: [
    AppComponent,
    SaveSecretComponent,
    RetrieveSecretComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
