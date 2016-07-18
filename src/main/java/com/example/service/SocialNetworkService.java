package com.example.service;

import com.example.domain.Amistad;
import com.example.domain.Pareja;
import com.example.domain.Persona;
import com.example.repository.AmistadRepository;
import com.example.repository.ParejaRepository;
import com.example.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SocialNetworkService {

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private ParejaRepository parejaRepository;
    @Autowired
    private AmistadRepository amistadRepository;

        // API PÚBLICO DE LA RED SOCIAL

    public void añadirPersona(Persona persona)
    {
       personaRepository.save(persona);
    }

    public void añadirPareja (Persona p1, Persona p2)
    {
        Pareja pareja = new Pareja(p1,p2);
        parejaRepository.save(pareja);
    }

    public Persona getPersona(Long id){

        return personaRepository.findOne(id);
    }

    public Persona getPersona(String nombre)
    {
       return personaRepository.findByNombre(nombre);
    }

    public Persona getPareja(Persona persona)
    {
        Pareja pareja = parejaRepository.getPareja(persona);

        Persona resultado = null;

        if (pareja==null)
        {
            return resultado;
        }

        else
        {
            if(pareja.getP1().equals(persona))
            {
                resultado= pareja.getP2();
            }

            else if(pareja.getP2().equals(persona))
            {
                resultado= pareja.getP1();
            }
        }

        return resultado;
    }

    public Amistad addAmistades(Persona persona1, Persona persona2)
    {
        /*TODO:gestionar el posible error de que estas dos personas
        ya pueden ser amigos*/

        return amistadRepository.
                save(new Amistad(persona1,persona2));

        //queremos añadir la amistad
        //pero también queremos mostrar el id generado
        //por la BD, por eso hacemos un return.
    }

    public List<Persona> getAmistades(Persona persona) {
        List<Amistad> amistades = amistadRepository.getAmistades(persona);

        List<Persona> resultado = new ArrayList<>();

        for (Amistad amistad : amistades)
        {
            if (amistad.getPersona1().equals(persona))
            {
                resultado.add(amistad.getPersona2());
            }
            else if (amistad.getPersona2().equals(persona))
            {
                resultado.add(amistad.getPersona1());
            }
        }

        return resultado;
    }




}
