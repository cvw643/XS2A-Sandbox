import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountAccessManagementComponent } from './account-access-management.component';

describe('AccountAccessManagementComponent', () => {
  let component: AccountAccessManagementComponent;
  let fixture: ComponentFixture<AccountAccessManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountAccessManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountAccessManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
