package com.invegorant.config.data;

import com.github.javafaker.Faker;

public class PracticeFormData {
    static Faker faker = new Faker();
    public static String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            currentAddress = faker.address().fullAddress(),
            subject = "Computer Science",
            sportHobby = "Sports",
            readingHobby = "Reading",
            musicHobby = "Music",
            state = "NCR",
            city = "Delhi";
}
