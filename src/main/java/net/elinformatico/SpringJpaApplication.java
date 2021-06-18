package net.elinformatico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.elinformatico.console.MenuConsoleJPA;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {

	@Autowired
	private MenuConsoleJPA menu;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.startShowingMenu();		
	}
}
