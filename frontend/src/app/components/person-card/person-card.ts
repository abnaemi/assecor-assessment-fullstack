import {Component, Input} from '@angular/core';
import {Person} from '../models/person';
import {RouterLink} from '@angular/router';
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from '@angular/material/card';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-person-card',
  imports: [
    RouterLink,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardSubtitle,
    MatCardContent,
    MatButton,
    MatCardActions
  ],
  templateUrl: './person-card.html',
  styleUrl: './person-card.css',
})

export class PersonCard {
  @Input({ required: true }) person!: Person;

  getColorHex(colorName: string): string {
    const colorMap: Record<string, string> = {
      'blau': '#3b82f6',
      'grün': '#22c55e',
      'violett': '#8b5cf6',
      'rot': '#ef4444',
      'gelb': '#eab308',
      'türkis': '#06b6d4',
      'weiß': '#FFFFFF'
    };
    return colorMap[colorName?.toLowerCase()] || '#cbd5e1';
  }
}
