import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {Component, Input, Pipe, PipeTransform} from '@angular/core';

import { EmbConsentPutComponent } from './emb-consent-put.component';
import {LineCommandComponent} from '../../../../../custom-elements/line-command/line-command.component';
import {CodeAreaComponent} from '../../../../../custom-elements/code-area/code-area.component';

describe('EmbConsentPutComponent', () => {
  let component: EmbConsentPutComponent;
  let fixture: ComponentFixture<EmbConsentPutComponent>;

  @Component({
    selector: 'app-play-wth-data',
    template: ''
  })
  class MockPlayWithDataComponent {
    @Input() headers: object;
    @Input() consentIdFlag: boolean;
    @Input() variablePathEnd: string;
    @Input() authorisationIdFlag: boolean;
  }

  @Pipe({name: 'translate'})
  class TranslatePipe implements PipeTransform {
    transform(value) {
      const tmp = value.split('.');
      return tmp[1];
    }
  }

  @Pipe({name: 'prettyJson'})
  class PrettyJsonPipe implements PipeTransform {
    transform(value) {
      return JSON.stringify(value, null, 4);
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        EmbConsentPutComponent,
        TranslatePipe,
        PrettyJsonPipe,
        LineCommandComponent,
        MockPlayWithDataComponent,
        CodeAreaComponent
      ],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmbConsentPutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be right headers', () => {
    const headers: object = {
      'X-Request-ID': '2f77a125-aa7a-45c0-b414-cea25a116035',
      'TPP-Explicit-Authorisation-Preferred': 'true',
      'PSU-ID': 'YOUR_USER_LOGIN',
      'PSU-IP-Address': '1.1.1.1',
    };
    expect(typeof component.headers).toBe('object');
    for (const key in component.headers) {
      if (component.headers.hasOwnProperty(key)) {
        expect(headers.hasOwnProperty(key)).toBeTruthy();
        expect(headers[key]).toBe(component.headers[key]);
      }
    }
  });

  it('should change segment', () => {
    expect(component.activeSegment).toBe('documentation');

    component.changeSegment('play-data');
    expect(component.activeSegment).toBe('play-data');

    component.changeSegment('documentation');
    expect(component.activeSegment).toBe('documentation');

    component.changeSegment('wrong-segment');
    expect(component.activeSegment).not.toBe('wrong-segment');
  });
});
