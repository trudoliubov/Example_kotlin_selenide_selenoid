package com.example.demo

import com.codeborne.selenide.Selectors.byId
import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selenide.element

class PersonPage {
    //PageObjects
    val surname = element(byId("formGridSurname0"))
    val confirmCode = element(byXpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[4]/div[6]/input"))
}