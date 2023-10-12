package cl.coopeuch.desafio.controllers;

import cl.coopeuch.desafio.repositories.TareaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TareaControllerTest {

    private final static String path = "/api/v1/tareas";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void deleteAllBeforeTests() throws Exception {
        tareaRepository.deleteAll();
    }

    @Test
    public void shouldReturnNotFoundAtIndex() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateTarea() throws Exception {

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descripcion\": \"Tarea\", \"vigente\": true}"))
                .andExpect(status().isCreated())
                .andDo((print()))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.fechaCreacion").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.descripcion").value("Tarea"))
                .andExpect(jsonPath("$.vigente").value(true));
    }

    @Test
    public void shouldReturnTarea() throws Exception {

        var result = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Tarea\", \"vigente\": true}"))
                .andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString();
        Map<String, Object> json
                = objectMapper.readValue(response, new TypeReference<Map<String,Object>>(){});
        Integer id = json.containsKey("id") ? (Integer) json.get("id") : 1;

        mockMvc.perform(get(path + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.fechaCreacion").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.descripcion").value("Tarea"))
                .andExpect(jsonPath("$.vigente").value(true));
    }

    @Test
    public void shouldReturnTareaAsList() throws Exception {
        // given
        mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Tarea\", \"vigente\": true}"));

        // when
        var response = mockMvc.perform(get(path));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.size()",
                        greaterThan(0))
                );
    }

    @Test
    public void shouldUpdateTarea() throws Exception {

        var result = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Tarea\", \"vigente\": true}"));

        String response = result.andReturn().getResponse().getContentAsString();
        Map<String, Object> json
                = objectMapper.readValue(response, new TypeReference<Map<String,Object>>(){});
        Integer id = json.containsKey("id") ? (Integer) json.get("id") : 1;

        mockMvc.perform(put(path+ "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Tarea\", \"vigente\": false}"))
                .andExpect(status().isOk());

        mockMvc.perform(get(path+"/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Tarea"))
                .andExpect(jsonPath("$.vigente").value(false));
    }

    @Test
    public void shouldDeleteTarea() throws Exception {

        var result = mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Tarea\", \"vigente\": true}"));

        String response = result.andReturn().getResponse().getContentAsString();
        Map<String, Object> json
                = objectMapper.readValue(response, new TypeReference<Map<String,Object>>(){});
        Integer id = json.containsKey("id") ? (Integer) json.get("id") : 1;


        mockMvc.perform(delete(path + "/{id}", id)).andExpect(status().isOk());
        mockMvc.perform(get(path + "/{id}", id)).andExpect(status().isNotFound());
    }
}
