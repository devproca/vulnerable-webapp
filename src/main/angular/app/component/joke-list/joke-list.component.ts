import {Component, OnInit} from '@angular/core';
import {JokeService} from "../../service/joke.service";
import {Joke} from "../../model/joke.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-joke-list',
  templateUrl: './joke-list.component.html',
  styleUrls: ['./joke-list.component.scss']
})
export class JokeListComponent implements OnInit {

  jokes: Joke[] = [];

  constructor(private jokeService: JokeService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.jokeService.findAll().subscribe(jokes => {
      this.jokes = jokes;
    });
  }

  editJoke(joke: Joke): void {
    this.router.navigateByUrl(`/jokes/${joke.jokeId}`);
  }

  newJoke(): void {
    this.router.navigateByUrl("/jokes/create");
  }
}
