package utils

import com.codeborne.selenide.Selenide.open

class SetUp {
    fun setUp() {
        open("https://test.baikalpass.ru")
    }
}