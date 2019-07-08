import { TestBed } from '@angular/core/testing';

import { SettingsHttpService } from './settings-http.service';

describe('SettingsHttpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SettingsHttpService = TestBed.get(SettingsHttpService);
    expect(service).toBeTruthy();
  });
});
