import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerDataComponent } from './owner-data.component';

describe('OwnerDataComponent', () => {
  let component: OwnerDataComponent;
  let fixture: ComponentFixture<OwnerDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerDataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
