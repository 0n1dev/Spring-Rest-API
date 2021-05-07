package kr.spring.restapi.events;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") // id 값만 가지고 Equals와 HashCode 비교
// @Data 애노테이션 사용시 상호참조가 발생할 수 있음 @EqualsAndHashCode가 모든 필드를 참조하기 때문
@Entity
public class Event {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;

    @Enumerated(EnumType.STRING) // ORDINAL로 숫자 값으로 저장되서 순서가 바뀌면 이슈가 발생
    private EventStatus eventsStatus;

}
