import { TestBed } from '@angular/core/testing';

import { OwnerLoginGuard } from './owner-login.guard';

describe('OwnerLoginGuard', () => {
  let guard: OwnerLoginGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(OwnerLoginGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
