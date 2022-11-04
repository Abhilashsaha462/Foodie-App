import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadercompComponent } from './headercomp.component';

describe('HeadercompComponent', () => {
  let component: HeadercompComponent;
  let fixture: ComponentFixture<HeadercompComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeadercompComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeadercompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
