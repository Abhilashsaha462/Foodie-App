import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantEditComponent } from './restaurant-edit.component';

describe('RestaurantEditComponent', () => {
  let component: RestaurantEditComponent;
  let fixture: ComponentFixture<RestaurantEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestaurantEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
