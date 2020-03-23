import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Persona } from '../Models/Persona';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) { }
  Url='http://localhost:8080/personas';

  
  getPersonas(){
    return this.http.get<Persona[]>(this.Url);
  }
  
  createPersona(persona:Persona){
    return this.http.post<Persona>(this.Url , persona);
  }

}
