package fitnessapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActivityController.class)
public class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Clear activities before each test
        ActivityController.activities.clear();
    }

    @Test
    public void testLogActivity() throws Exception {
        String activityJson = "{"userId":"123","type":"Running","duration":30,"intensity":"High"}";
        
        mockMvc.perform(post("/api/activities")
                .contentType("application/json")
                .content(activityJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Running"))
                .andExpect(jsonPath("$.duration").value(30))
                .andExpect(jsonPath("$.intensity").value("High"));
    }

    @Test
    public void testGetActivities() throws Exception {
        String activityJson = "{"userId":"123","type":"Running","duration":30,"intensity":"High"}";
        
        mockMvc.perform(post("/api/activities")
                .contentType("application/json")
                .content(activityJson));
        
        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].type").value("Running"));
    }

    @Test
    public void testUpdateActivity() throws Exception {
        String activityJson = "{"userId":"123","type":"Running","duration":30,"intensity":"High"}";
        String updatedActivityJson = "{"userId":"123","type":"Cycling","duration":45,"intensity":"Medium"}";
        
        // Log an activity
        String response = mockMvc.perform(post("/api/activities")
                .contentType("application/json")
                .content(activityJson))
                .andReturn().getResponse().getContentAsString();
        
        // Extract the ID from the response
        String id = response.split(""id":"")[1].split(""[,"])[0];
        
        // Update the activity
        mockMvc.perform(put("/api/activities/" + id)
                .contentType("application/json")
                .content(updatedActivityJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Cycling"))
                .andExpect(jsonPath("$.duration").value(45))
                .andExpect(jsonPath("$.intensity").value("Medium"));
    }

    @Test
    public void testDeleteActivity() throws Exception {
        String activityJson = "{"userId":"123","type":"Running","duration":30,"intensity":"High"}";
        
        // Log an activity
        String response = mockMvc.perform(post("/api/activities")
                .contentType("application/json")
                .content(activityJson))
                .andReturn().getResponse().getContentAsString();
        
        // Extract the ID from the response
        String id = response.split(""id":"")[1].split(""[,"])[0];
        
        // Delete the activity
        mockMvc.perform(delete("/api/activities/" + id))
                .andExpect(status().isOk());
        
        // Verify the activity is deleted
        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}