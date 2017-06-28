package com.movingtoweb.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movingtoweb.model.Users;
import com.movingtoweb.repository.UsersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {


	private static TestRestTemplate restTemplate;

	@Autowired
	private static UsersRepository usersRepository;
	
	


	@Test
	public void testUsers() {

		List<Users> body = this.restTemplate.getForObject("http://localhost:8080/users/name/Ajay", List.class);
		assertThat(body.toString()).isEqualTo("[{id=1, name=Ajay, teamName=Development, salary=100}]");
	}

	@BeforeClass
	public static void initialize() {
		restTemplate= new TestRestTemplate();
		try {
			// Open the file that is the first
			// command line parameter

			File file = new File("https://storage.googleapis.com/user-test-data-1/LoadData.txt");

			FileInputStream fstream = new FileInputStream(file);

			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String jsonData;

			List<Users> users = new ArrayList<>();

			Long index=1L;
			// Read File Line By Line
			while ((jsonData = br.readLine()) != null) {
				System.out.println("Data : " + jsonData);
				Users user = new ObjectMapper().readValue(jsonData, Users.class);
				users.add(user);
				//user.setId(index++);
				//System.out.println("conveterd object  : " + user.toString());
			     //restTemplate.postForObject("http://localhost:8080/users/load", jsonData, String.class);
			    
			}
		    restTemplate.postForObject("http://localhost:8080/users/multipleData", users, List.class);
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

}
