package com.example.demo

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide.element
import org.testng.annotations.*

class MainPageTest {
    private val mainPage = MainPage()
    private val personPage = PersonPage()
    private val setUp = SetUp()

    @BeforeMethod
    fun setup(){
        setUp.setUp()
    }

    @Test
    fun search() {
        mainPage.nextBtn.click()
        personPage.surname.sendKeys("Aleksandr")
        element(byId("formGridSurname0")).shouldHave(value("Aleksandr"))
    }
}