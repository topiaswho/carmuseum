package com.example.demo;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarRepository;
import com.example.demo.domain.Location;
import com.example.demo.domain.LocationRepository;
import com.example.demo.web.CarController;
import com.example.demo.web.LocationController;
import com.example.demo.web.OwnerController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarmuseumApplicationTests {

    @Autowired
    private CarController carController;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired 
    private LocationController locationController;
    
    @Autowired 
    private OwnerController ownerController;
    
    @Autowired
    private MockMvc mockMvc;

    
    // Smoketests
    @Test
    void contextLoads() {
        assertThat(carController).isNotNull();
        assertThat(locationController).isNotNull();
        assertThat(ownerController).isNotNull();
    }
    
    @Test
    void testCarController() throws Exception {
    	 
    	// Tests the endpoint that displays the list of cars
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/carmuseum"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("cars"));
    }
    
    // Tests the location creation
    
    @Test
    public void createNewLocation() {
    Location location = new Location("Joensuu");
    locationRepository.save(location);
    assertThat(location.getlocationName()).isNotNull();
    }
}
