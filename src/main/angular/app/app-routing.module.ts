import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./component/login/login.component";
import {RegistrationComponent} from "./component/registration/registration.component";
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {JokeListComponent} from "./component/joke-list/joke-list.component";
import {AuthGuard} from "./guard/auth.guard";
import {JokeEditComponent} from "./component/joke-edit/joke-edit.component";

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
  {
    path: 'jokes/create',
    component: JokeEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'jokes/:jokeId',
    component: JokeEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    component: LoginComponent
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
