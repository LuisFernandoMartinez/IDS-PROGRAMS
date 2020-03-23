import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/Service/service.service';
import { Persona } from 'src/app/Models/Persona';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  
  persona:Persona[];
  constructor(private router:Router, private service:ServiceService) { }

  model: Persona ={id:0 , name:'',apellidos:''}; 

  ngOnInit() {
  }

  Guardar(persona:Persona){   
    console.log(this.model);
    this.service.createPersona(this.model)
    .subscribe(data=>{
      this.router.navigate(["listar"]);
    })
     alert("AÑADIDO A BASE");
  }
  Guardar2(){
    console.log(this.model);
  }



}
