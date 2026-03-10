import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { PersonService } from '../../services/service';
import { Person } from '../models/person';
import { PersonCard } from '../person-card/person-card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSidenavModule } from '@angular/material/sidenav';

@Component({
  selector: 'app-person-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    PersonCard,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    MatSidenavModule
  ],
  templateUrl: './person-list.html',
  styleUrl: './person-list.css',
})
export class PersonList implements OnInit {
  private personService = inject(PersonService);

  allPersons = signal<Person[]>([]);
  filteredPersons = signal<Person[]>([]);
  searchTerm = signal<string>('');
  selectedColorFilter = signal<string>('all');
  sortKey = signal<keyof Person>('lastname');
  sortDirection = signal<'asc' | 'desc'>('asc');
  availableColors = [
    'Blau',
    'Grün',
    'Violett',
    'Rot',
    'Gelb',
    'Türkis',
    'Weiß'
  ];
  personForm = new FormGroup({
    name: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    lastname: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    zipcode: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    city: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    color: new FormControl('', { nonNullable: true, validators: [Validators.required] })
  });

  ngOnInit(): void {
    this.refreshData();
  }

  refreshData(): void {
    this.personService.getPersons().subscribe((data) => {
      this.allPersons.set(data);
      this.applyFilterAndSort();
    });
  }

  onSearchChange(term: string): void {
    this.searchTerm.set(term);
    this.applyFilterAndSort();
  }

  onColorFilterChange(color: string): void {
    this.selectedColorFilter.set(color);
    this.applyFilterAndSort();
  }

  changeSort(key: any): void {
    const newKey = key as keyof Person;
    if (this.sortKey() === newKey) {
      this.sortDirection.update(d => d === 'asc' ? 'desc' : 'asc');
    } else {
      this.sortKey.set(newKey);
      this.sortDirection.set('asc');
    }
    this.applyFilterAndSort();
  }

  applyFilterAndSort(): void {
    let result = this.allPersons();

    if (this.selectedColorFilter() !== 'all') {
      result = result.filter(p => p.color.toLowerCase() === this.selectedColorFilter().toLowerCase());
    }

    const term = this.searchTerm().toLowerCase();
    if (term) {
      result = result.filter(p =>
        Object.values(p).some(val => String(val).toLowerCase().includes(term))
      );
    }

    const key = this.sortKey();
    const isAsc = this.sortDirection() === 'asc';
    result = [...result].sort((a, b) => {
      const valA = String(a[key] ?? '').toLowerCase();
      const valB = String(b[key] ?? '').toLowerCase();
      return valA.localeCompare(valB, undefined, { numeric: true }) * (isAsc ? 1 : -1);
    });

    this.filteredPersons.set(result);
  }

  savePerson(): void {
    if (this.personForm.valid) {
      this.personService.createPerson(this.personForm.getRawValue() as Person).subscribe({
        next: (newPerson) => {
          this.allPersons.update(list => [...list, newPerson]);
          this.applyFilterAndSort();
          this.personForm.reset();
        },
        error: (err) => console.error('Fehler beim Speichern', err)
      });
    }
  }
}
