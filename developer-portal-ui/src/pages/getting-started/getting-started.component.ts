import { Component, OnInit } from '@angular/core';
import { CustomizeService, Theme } from '../../services/customize.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-getting-started',
  templateUrl: './getting-started.component.html',
  styleUrls: ['./getting-started.component.scss'],
})
export class GettingStartedComponent implements OnInit {
  defaultTheme: Theme;

  constructor(private customizeService: CustomizeService) {}

  exportTheme() {
    const blob = new Blob([JSON.stringify(this.defaultTheme, null, 2)], {
      type: 'application/json',
    });
    saveAs(blob, 'exampleTheme');
  }

  ngOnInit() {
    this.defaultTheme = this.customizeService.getTheme('default');
  }
}
