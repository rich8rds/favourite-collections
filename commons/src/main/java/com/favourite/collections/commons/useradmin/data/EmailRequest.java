package com.favourite.collections.commons.useradmin.data;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class EmailRequest {

    String to;
    String subject;
    String body;
}
