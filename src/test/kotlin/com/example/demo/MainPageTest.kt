package com.example.demo

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide.element
import org.testng.annotations.*
import utils.CheckingMails
import utils.CheckingMails.code
import utils.SetUp

class MainPageTest {
    private val mainPage = MainPage()
    private val personPage = PersonPage()
    private val setUp = SetUp()


    @BeforeMethod
    fun setup(){
        setUp.setUp()
    }

    @Test(priority = 0)
    fun confirmCode() {
        mainPage.nextBtn.click()
        CheckingMails.check("imap.gmail.com", "993", "somemail@gmail.com", "password")
        personPage.confirmCode.sendKeys(code)
        element(byXpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[4]/div[6]/input")).shouldHave(value("+$code"))
    }
    @Test(priority = 1)
    fun Surname() {
        mainPage.nextBtn.click()
        personPage.surname.sendKeys("Aleksandr")
        element(byId("formGridSurname0")).shouldHave(value("Aleksandr"))
    }
}