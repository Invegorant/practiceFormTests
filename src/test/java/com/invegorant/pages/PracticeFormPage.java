package com.invegorant.pages;

import com.invegorant.config.data.PracticeFormData;
import com.invegorant.pages.components.CalendarComponent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormPage {

    public CalendarComponent calendar = new CalendarComponent();

    private final SelenideElement firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            genderMale = $("input[value='Male']"),
            userNumber = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            uploadFile = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            submit = $("#submit");
    ElementsCollection hobbies = $$("#hobbiesWrapper .custom-control-label");

    public PracticeFormPage typeFirstName(String firstName) {
        this.firstName.setValue(firstName);
        return this;
    }

    public PracticeFormPage typeLastName(String lastName) {
        this.lastName.setValue(lastName);
        return this;
    }

    public PracticeFormPage typeEmail(String email) {
        userEmail.setValue(email);
        return this;
    }

    public PracticeFormPage chooseMaleGender() {
        genderMale.click(usingJavaScript());
        return this;
    }

    public PracticeFormPage typeUserNumber(String userNumber) {
        this.userNumber.setValue(userNumber);
        return this;
    }

    public PracticeFormPage typeSubject(String subject) {
        subjectInput.setValue(subject).pressEnter();
        return this;
    }

    public PracticeFormPage typeHobby(String hobby) {
        hobbies.findBy(text(hobby)).click();
        return this;
    }

    public PracticeFormPage uploadFile() {
        uploadFile.uploadFile(new File("src/test/resources/img/n7_pictures.png"));
        return this;
    }

    public PracticeFormPage typeCurrentAddress(String currentAddress) {
        this.currentAddress.setValue(currentAddress);
        return this;
    }

    public PracticeFormPage chooseState(String state) {
        this.state.click();
        this.state.$(byText(state)).click();
        return this;
    }

    public PracticeFormPage chooseCity(String city) {
        this.city.click();
        this.city.$(byText(city)).click();
        return this;
    }

    public void submitForm() {
        submit.click();
    }

    public void verifyFormIsCompleted() {
        ElementsCollection checkRightForm = $$(".table-responsive tbody tr").snapshot();
        for (SelenideElement form : checkRightForm) {
            String key = form.$("td").text();
            String expectedValue = expectedData.get(key);
            String actualValue = form.$("td", 1).text();
            assertEquals(actualValue, expectedValue);
        }
    }

    Map<String, String> expectedData = new HashMap() {{
        put("Student Name", PracticeFormData.firstName + " " + PracticeFormData.lastName);
        put("Student Email", PracticeFormData.userEmail);
        put("Gender", "Male");
        put("Mobile", PracticeFormData.userNumber);
        put("Date of Birth", "26 May,1998");
        put("Subjects", PracticeFormData.subject);
        put("Hobbies", PracticeFormData.sportHobby + ", " + PracticeFormData.readingHobby + ", " + PracticeFormData.musicHobby);
        put("Picture", "n7_pictures.png");
        put("Address", PracticeFormData.currentAddress);
        put("State and City", PracticeFormData.state + " " + PracticeFormData.city);
    }};
}