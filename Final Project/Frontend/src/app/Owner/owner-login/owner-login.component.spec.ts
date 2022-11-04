import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerLoginComponent } from './owner-login.component';

describe('OwnerLoginComponent', () => {
  let component: OwnerLoginComponent;
  let fixture: ComponentFixture<OwnerLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
