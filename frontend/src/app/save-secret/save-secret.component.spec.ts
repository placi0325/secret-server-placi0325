import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveSecretComponent } from './save-secret.component';

describe('SaveSecretComponent', () => {
  let component: SaveSecretComponent;
  let fixture: ComponentFixture<SaveSecretComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SaveSecretComponent]
    });
    fixture = TestBed.createComponent(SaveSecretComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
