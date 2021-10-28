import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {JokeListComponent} from "./joke-list/joke-list.component";
import {AuthGuard} from "./guard/auth.guard";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegistrationComponent
  },
  {
    path: 'jokes',
    component: JokeListComponent,
    canActivate: [AuthGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [{
    provide: LocationStrategy, useClass: HashLocationStrategy
  }]
})
export class AppRoutingModule {

}
