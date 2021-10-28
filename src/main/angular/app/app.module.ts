import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {RegistrationComponent} from './component/registration/registration.component';
import {LoginComponent} from './component/login/login.component';
import {JokeListComponent} from './component/joke-list/joke-list.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {SafeHtmlPipe} from "./pipe/safe-html.pipe";
import {JokeEditComponent} from './component/joke-edit/joke-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    JokeListComponent,
    SafeHtmlPipe,
    JokeEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
