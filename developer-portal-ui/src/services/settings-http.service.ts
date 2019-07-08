import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SettingsService } from './settings.service';
import { EnvLinks } from '../models/envLinks.model';

@Injectable({
  providedIn: 'root',
})
export class SettingsHttpService {
  constructor(
    private http: HttpClient,
    private settingsService: SettingsService
  ) {}

  initializeApp(): Promise<any> {
    return new Promise(resolve => {
      this.http
        .get('assets/UI/envLinks.json')
        .toPromise()
        .then(response => {
          console.log(this.settingsService.settings);
          this.settingsService.settings.envLinks = <EnvLinks>response;
          resolve();
        });
    });
  }
}
