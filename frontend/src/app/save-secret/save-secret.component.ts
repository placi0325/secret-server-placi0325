import { Component } from '@angular/core';
import { SecretService } from '../secret.service';

@Component({
  selector: 'app-save-secret',
  templateUrl: './save-secret.component.html',
  styleUrls: ['./save-secret.component.css']
})
export class SaveSecretComponent {
  constructor(private SecretService: SecretService){}

  secretText: string = '';
  expiresAt: number = 0; //| null = null;
  remainingViews: number = 0;

  hash: string = '';


  submitted : boolean = false;

  onSubmit(){
    this.SecretService.postSecret(this.secretText, this.expiresAt, this.remainingViews)
      .subscribe((response) => {
          this.hash = response.hash;
          this.submitted = true;
    })
  }
}
