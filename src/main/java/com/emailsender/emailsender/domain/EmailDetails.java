package com.emailsender.emailsender.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
