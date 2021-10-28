import {Component, OnInit} from '@angular/core';
import {JokeService} from "../service/joke.service";
import {Joke} from "../model/joke.model";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-joke-list',
  templateUrl: './joke-list.component.html',
  styleUrls: ['./joke-list.component.scss']
})
export class JokeListComponent implements OnInit {

  jokes: Joke[] = [];

  constructor(private jokeService: JokeService,
              private domSanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.jokeService.findAll().subscribe(jokes => {
      this.jokes = jokes;

    });
  }



}
