import {Component, inject, OnInit, signal} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {PersonService} from '../../services/service';
import {Person} from '../models/person';
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from '@angular/material/card';
import {MatIcon} from '@angular/material/icon';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-person-detail',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardSubtitle,
    MatCardContent,
    MatCardActions,
    MatIcon,
    MatButton,
    RouterLink
  ],
  templateUrl: './person-detail.html',
  styleUrl: './person-detail.css',
})
export class PersonDetail implements OnInit {
  private route = inject(ActivatedRoute);
  private personService = inject(PersonService);

  person = signal<Person | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.personService.getPersonById(id).subscribe(data => this.person.set(data));
    }
  }
}
