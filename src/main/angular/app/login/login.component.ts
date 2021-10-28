import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  formGroup = this.createFormGroup();
  errors: any = {};

  constructor(private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService,
              private router: Router) {
  }

  login(): void {
    const credentials = this.formGroup.value;
    this.formGroup.disable({emitEvent: false});
    this.authenticationService.login(credentials).subscribe(
      () => this.redirectToJokes(),
      httpError => {
        if (httpError.status === 400) {
          this.errors = httpError.error;
        }
      }, () => {
        this.formGroup.enable({emitEvent: false});
      });
  }

  register(): void {
    this.router.navigateByUrl("/register");
  }

  private redirectToJokes(): void {
    this.router.navigateByUrl("/jokes");
  }

  private createFormGroup(): FormGroup {
    return this.formBuilder.group({
      "username": null,
      "password": null
    });
  }

}
