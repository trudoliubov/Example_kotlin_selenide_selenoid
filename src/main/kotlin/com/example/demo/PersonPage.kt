package com.example.demo

import com.codeborne.selenide.Selectors.byId
import com.codeborne.selenide.Selenide.element

class PersonPage {
    //PageObjects
    val surname = element(byId("formGridSurname0"))
}