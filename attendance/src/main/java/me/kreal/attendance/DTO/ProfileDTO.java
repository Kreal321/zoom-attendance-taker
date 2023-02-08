package me.kreal.attendance.DTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProfileDTO {

    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String pic_url;

    public String getFullName() {
        return first_name + " " + last_name;
    }

}
