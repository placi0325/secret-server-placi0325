import { Component } from '@angular/core';
import { SecretService } from '../secret.service';

@Component({
  selector: 'app-retrieve-secret',
  templateUrl: './retrieve-secret.component.html',
  styleUrls: ['./retrieve-secret.component.css']
})
export class RetrieveSecretComponent {
  constructor(private SecretService: SecretService) { }

  hash : string = '';
  secret : string = '';

  submitted: boolean = false;

  onSubmit(){ 
    this.SecretService.getSecretByHash(this.hash)
      .subscribe((response) => {
        this.secret = response.secret;
        this.submitted = true;
    })
  }
}
