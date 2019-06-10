import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-accinf-balance-get',
  templateUrl: './accinf-balance-get.component.html'
})
export class AccinfBalanceGetComponent implements OnInit {

  activeSegment = 'documentation';
  headers: object = {};
  body: object = {};

  constructor() {
    this.init();
  }

  init() {
    this.headers = {
      'X-Request-ID': '2f77a125-aa7a-45c0-b414-cea25a116035',
      'Consent-ID': 'CONSENT_ID',
      'PSU-IP-Address': '1.1.1.1',
    };
  }

  changeSegment(segment) {
    this.activeSegment = segment;
  }

  ngOnInit() {}

}
