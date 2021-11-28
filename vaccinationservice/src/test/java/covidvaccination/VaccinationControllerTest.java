package covidvaccination;

import static covidvaccination.Utils.JsonString.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import covidvaccination.controllers.VaccinationsController;
import covidvaccination.models.Vaccination;
import covidvaccination.repositories.VaccinationRepository;

import covidvaccination.services.VaccinationService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@WebMvcTest(VaccinationsController.class)
@AutoConfigureMockMvc
public class VaccinationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaccinationService service;

    @Test
    @DisplayName("GET /vaccinations/0 - Found")
    void testGetVaccinationByIdFound() throws Exception {

        // Set up the mock service
        LocalDate date = LocalDate.of(2020, 1, 8);
        Vaccination mockCovidTest = new Vaccination("Covid-19", "Pfizer", "Covid-19", "mtp", "FR", 1, date, "1");
        System.out.println(mockCovidTest);
        doReturn(mockCovidTest).when(service).findVaccinationById(Long.valueOf("0"));

        // Execute the Get Request
        mockMvc.perform(get("/covidtests/{id}",0))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //Validate the response code and content type
                //.andExpect(header().string(HttpHeaders.ETAG, "\"0\""))
                //.andExpect(header().string(HttpHeaders.LOCATION, "/covidtests/0"))

                //Validate the returned fields
                .andExpect(jsonPath("$.length()", is(8)))
                .andExpect(jsonPath("$.vaccine_name", is("Covid-19")))
                .andExpect(jsonPath("$.vaccine_type", is("Pfizer")))
                .andExpect(jsonPath("$.target_disease", is("Covid-19")))
                .andExpect(jsonPath("$.vaccination_center", is("mtp")))
                .andExpect(jsonPath("$.country", is("FR")))
                .andExpect(jsonPath("$.number_of_doses", is(1)))
                .andExpect(jsonPath("$.vaccination_date", is(date)))
                .andExpect(jsonPath("$.user_id", is("1")));
    }



    @Test
    @DisplayName("Post /vaccinations - Success")
    void testCreateCovidTest() throws Exception {

        // Set up the mock service
        LocalDate date = LocalDate.of(2020, 1, 8);
        System.out.println("###########");
        System.out.println(date.toString());
        System.out.println("###########");
        Vaccination mockVaccination = new Vaccination("Covid-19", "Pfizer", "Covid-19", "mtp", "FR", 1, date, "1");
        Vaccination postVaccination = new Vaccination("Covid-19", "Pfizer", "Covid-19", "mtp", "FR", 1, date, "1");
        doReturn(mockVaccination).when(service).addVaccination(any());

        // Execute the Get Request
        mockMvc.perform(post("/covidtests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postVaccination)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the response code and content type
                //.andExpect(header().string(HttpHeaders.ETAG, "\"0\""))
                //.andExpect(header().string(HttpHeaders.LOCATION, "/covidtests/0"))

                //Validate the returned fields
                .andExpect(jsonPath("$.length()", is(8)))
                .andExpect(jsonPath("$.vaccine_name", is("Covid-19")))
                .andExpect(jsonPath("$.vaccine_type", is("Pfizer")))
                .andExpect(jsonPath("$.target_disease", is("Covid-19")))
                .andExpect(jsonPath("$.vaccination_center", is("mtp")))
                .andExpect(jsonPath("$.country", is("FR")))
                .andExpect(jsonPath("$.number_of_doses", is(1)))
                .andExpect(jsonPath("$.vaccination_date", is(date)))
                .andExpect(jsonPath("$.user_id", is("1")));
    }



}
