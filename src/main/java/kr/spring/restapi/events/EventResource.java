package kr.spring.restapi.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class EventResource extends RepresentationModel {

    @JsonUnwrapped
    private Event event;
}
