import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecretService {

  private mainUrls = {
    postSecret: "/v1/secret",
    getSecretByHash: "/v1/secret/",
  };

  constructor(private http: HttpClient) { }

  public postSecret(secretText: string, expiresAt: number, remainingViews: number): Observable<any> {

    let body = {
      secretText: secretText,
      expiresAt: expiresAt,
      remainingViews: remainingViews
    }

    return this.http.post(this.mainUrls.postSecret, body);
  }

  public getSecretByHash(hash: string): Observable<any> {
    let urlWithHash = this.mainUrls.getSecretByHash + hash;
    return this.http.get(urlWithHash);
  }
  
}
