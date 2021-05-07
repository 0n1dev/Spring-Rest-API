package kr.spring.restapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EventRepository eventRepository;

    @Test
    public void createEvent() throws Exception {
        EventDto eventDto = EventDto.builder()
                .name("Spring")
                .description("Spring Test")
                .beginEnrollmentDateTime(LocalDateTime.of(2021, 5, 5, 10, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2021, 5, 7, 10, 0))
                .beginEventDateTime(LocalDateTime.of(2021, 5, 12, 10, 0))
                .endEventDateTime(LocalDateTime.of(2021,5,13,10,0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
                .build();

        mockMvc.perform(post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(new MediaType("application", "hal+json", StandardCharsets.UTF_8)) // 한글이 자꾸 깨짐
                    .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(new MediaType("application", "hal+json", StandardCharsets.UTF_8)))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-events").exists())
                .andExpect(jsonPath("_links.update-event").exists());
    }

    @Test
    @DisplayName("베드 리퀘스트 테스트")
    public void createEvent_Bad_Request() throws Exception {
        EventDto eventDto = EventDto.builder()
                .name("Spring")
                .description("Spring Test")
                .beginEnrollmentDateTime(LocalDateTime.of(2021, 5, 5, 10, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2021, 5, 7, 10, 0))
                .beginEventDateTime(LocalDateTime.of(2021, 5, 15, 10, 0))
                .endEventDateTime(LocalDateTime.of(2021,5,13,10,0))
                .basePrice(1000)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
                .build();


        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("입력 값이 잘못된 경우에 에러가 발생")
    public void createEvent_Bad_Request_Wrong_Input() throws Exception {
        EventDto eventDto = EventDto.builder()
                .name("Spring")
                .description("Spring Test")
                .beginEnrollmentDateTime(LocalDateTime.of(2021, 5, 5, 10, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2021, 5, 7, 10, 0))
                .beginEventDateTime(LocalDateTime.of(2021, 5, 15, 10, 0))
                .endEventDateTime(LocalDateTime.of(2021,5,13,10,0))
                .basePrice(1000)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
                .build();


        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].objectName").exists())
                .andExpect(jsonPath("$[0].field").exists())
                .andExpect(jsonPath("$[0].defaultMessage").exists())
                .andExpect(jsonPath("$[0].code").exists())
                .andExpect(jsonPath("$[0].rejectedValue").exists());
    }
}
