import { Routes } from '@angular/router';
import {PersonList} from './components/person-list/person-list';
import {PersonDetail} from './components/person-detail/person-detail';


export const routes: Routes = [
  { path: '', component: PersonList },
  { path: 'person/:id', component: PersonDetail }, // Die Detail-Route
  { path: '**', redirectTo: '' }
];
