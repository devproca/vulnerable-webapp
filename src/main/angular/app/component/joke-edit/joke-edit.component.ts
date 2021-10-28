import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpStatusCode} from "@angular/common/http";
import {JokeService} from "../../service/joke.service";

@Component({
  selector: 'app-joke-edit',
  templateUrl: './joke-edit.component.html',
  styleUrls: ['./joke-edit.component.scss']
})
export class JokeEditComponent implements OnInit {

  formGroup = this.createFormGroup();
  errors: any = {};

  constructor(private formBuilder: FormBuilder,
              private jokeService: JokeService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      const jokeId = params.get("jokeId") as string;
      this.refreshJoke(jokeId);
    })
  }

  save(): void {
    const joke = this.formGroup.value;
    this.formGroup.disable({emitEvent: false});
    this.jokeService.save(joke).subscribe(
      v => {
        this.formGroup.patchValue(v);
        this.redirectToJokeList();
      },
      httpError => {
        this.formGroup.enable({emitEvent: false});
        if (httpError.status === HttpStatusCode.BadRequest) {
          this.errors = httpError.error;
        }
      });
  }

  redirectToJokeList(): void {
    this.router.navigateByUrl("/jokes");
  }

  private refreshJoke(jokeId: string): void {
    this.jokeService.get(jokeId).subscribe(joke => {
      this.formGroup.patchValue(joke);
    });
  }

  private createFormGroup(): FormGroup {
    return this.formBuilder.group({
      "jokeId": null,
      "setup": null,
      "punchline": null
    });
  }
}
