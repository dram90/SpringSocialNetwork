package com.example;

import com.example.domain.Persona;
import com.example.service.SocialNetworkService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(DemoApplication.class, args);


		SocialNetworkService socialNetworkService =
				context.getBean(SocialNetworkService.class);


		testSocialNetwork(socialNetworkService);

	}

	private static void testSocialNetwork(SocialNetworkService socialNetworkService) {
		Persona ivan = new Persona();
		ivan.setNombre("Ivan");
		ivan.setEdad(23);

		Persona daniel = new Persona();
		daniel.setNombre("Daniel");
		daniel.setEdad(25);

		Persona novia = new Persona();
		novia.setNombre("novia");
		novia.setEdad(23);

		Persona ismael = new Persona();
		ismael.setNombre("Ismael");
		ismael.setEdad(29);

		Persona judith = new Persona();
		judith.setNombre("Judith");
		judith.setEdad(25);

		socialNetworkService.añadirPersona(ivan);
		socialNetworkService.añadirPersona(daniel);
		socialNetworkService.añadirPersona(novia);
		socialNetworkService.añadirPersona(ismael);
		socialNetworkService.añadirPersona(judith);

		socialNetworkService.añadirPareja(ivan,novia);

		System.out.println("La pareja de ivan es ");
		System.out.println(socialNetworkService.getPareja(ivan));

		socialNetworkService.addAmistades(ivan,ismael);
		socialNetworkService.addAmistades(judith,ivan);

		System.out.println("Los amigos de Ivan son: ");
		System.out.println(socialNetworkService.getAmistades(ivan));



	}
}
