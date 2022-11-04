import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantAddComponent } from './restaurant-add.component';

describe('RestaurantAddComponent', () => {
  let component: RestaurantAddComponent;
  let fixture: ComponentFixture<RestaurantAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestaurantAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
