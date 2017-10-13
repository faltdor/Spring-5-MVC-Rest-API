package com.faltdor.api.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.faltdor.api.domain.Category;
import com.faltdor.api.repositories.ICategoryRepository;

@Component
public class Bootstrap implements CommandLineRunner{
	
	private final ICategoryRepository categoryRepository;
	
	public Bootstrap(ICategoryRepository categoryRepository) {
		this.categoryRepository =categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Category fruits = new Category();
		fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");
        
        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
        
        System.out.println("Total of Categories: " + categoryRepository.count());
        
	}

}
