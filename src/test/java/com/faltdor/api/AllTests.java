package com.faltdor.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.faltdor.api.controllers.v1.CategoryControllerTest;
import com.faltdor.api.controllers.v1.CustomerControllerTest;
import com.faltdor.api.services.CategoryServiceImplTest;
import com.faltdor.api.services.CustomerServiceImplTest;
import com.faltdor.api.v1.model.mapper.CustomerMapperImplTest;
import com.faltdor.api.v1.model.mapper.ICategoryMapperTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	CategoryControllerTest.class,
	CustomerControllerTest.class,
	CategoryServiceImplTest.class,
	CustomerServiceImplTest.class,
	CustomerMapperImplTest.class,
	ICategoryMapperTest.class






})
public class AllTests {

}
