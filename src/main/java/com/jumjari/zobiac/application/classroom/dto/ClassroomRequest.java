package com.jumjari.zobiac.application.classroom.dto;

import com.jumjari.zobiac.domain.classroom.entity.Direction;
import com.jumjari.zobiac.domain.classroom.entity.DoorType;
import com.jumjari.zobiac.domain.classroom.entity.Status;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassroomRequest {
    private Long id;
    @NotNull(message = "건물을 선택하세요")
    private Long building;
    @NotBlank(message = "호수를 입력하세요")
    @Size(max = 6, message = "호수가 너무 깁니다")
    @Pattern(regexp = "^[0-9-]+$", message = "숫자와 붙임표(하이픈)만 입력하세요")
    @Pattern(regexp = ".*[^-]", message = "붙임표(하이픈)로 끝내지 마세요")
    private String number;
    private Byte floor;
    @Size(max = 100, message = "이름이 너무 깁니다")
    private String name;
    @NotNull(message = "방향을 입력하세요")
    private Direction direction;
    @NotNull(message = "문 타입을 입력하세요")
    private DoorType doorType;
    @NotNull(message = "문 개수를 입력하세요")
    @Min(value = 0, message = "0 이상 정수를 입력하세요")
    private Byte count;
    private Status status;
    private Long parentId;
    private String memo;

    public static ClassroomRequest formObject(Long buildingId) {
        ClassroomRequest dto = new ClassroomRequest();
        dto.setBuilding(buildingId);
        dto.setDoorType(DoorType.IDK);
        return dto;
    }
}