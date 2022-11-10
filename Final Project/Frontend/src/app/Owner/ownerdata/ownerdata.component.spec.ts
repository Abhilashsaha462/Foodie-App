import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerdataComponent } from './ownerdata.component';

describe('OwnerdataComponent', () => {
  let component: OwnerdataComponent;
  let fixture: ComponentFixture<OwnerdataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerdataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerdataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
