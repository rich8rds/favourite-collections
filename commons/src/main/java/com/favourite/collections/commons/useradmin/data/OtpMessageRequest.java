package com.favourite.collections.commons.useradmin.data;

import com.favourite.collections.commons.useradmin.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OtpMessageRequest implements Serializable {
    private String email;
    private String name;
    private String otp;
    private NotificationType type;
}