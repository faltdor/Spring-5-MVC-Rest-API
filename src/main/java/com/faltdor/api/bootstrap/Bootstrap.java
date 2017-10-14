package com.faltdor.api.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.faltdor.api.domain.Category;
import com.faltdor.api.domain.Customer;
import com.faltdor.api.repositories.ICategoryRepository;
import com.faltdor.api.repositories.ICustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner{
	
	private final ICategoryRepository categoryRepository;
	private final ICustomerRepository customerRepository;
	
	public Bootstrap(ICategoryRepository categoryRepository,ICustomerRepository customerRepository) {
		this.categoryRepository =categoryRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		loadCategories();
        loadCustomers();
	}

	private void loadCustomers() {
		Customer customer = new Customer();
		customer.setFirstname("Michael");
		customer.setLastname("Lachappele");
		
		Customer customer2 = new Customer();
		customer.setFirstname("Joe");
		customer.setLastname("Newman");
		customerRepository.save(customer);
		customerRepository.save(customer2);
	}

	private void loadCategories() {
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
