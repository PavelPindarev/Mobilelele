package com.example.mobilelele.service;

import java.util.Locale;

public interface EmailService {

    void sendRegistrationEmail(String userEmail,
                               String userName,
                               Locale preferredLocale);

}
