package com.example.demo

import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selenide.element

class MainPage {
    //PageObjects
    val nextBtn = element(byXpath("//*[@id=\"root\"]/div[2]/div/div[2]/a[1]"))
}
