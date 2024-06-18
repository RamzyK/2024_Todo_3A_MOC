package com.example.myfirstapp

import com.example.myfirstapp.utils.verifyPassword
import org.junit.Test
import org.junit.Assert.*

class MainActivityTodoTest {
    @Test
    fun `Check Valid Password`() {
        // Setup
        val password = "Password123!"
        // Test
        val errors = verifyPassword(password)
        // Resultat / Check / Assertion
        assertTrue(errors.isEmpty())
    }

    @Test
    fun `Check Password Length`() {
        // Setup
        val password = "123"
        // Test
        val errors = verifyPassword(password)
        // Resultat / Check / Assertion
        assertTrue(errors.contains("Le mot de passe doit contenir au moins 6 caractères."))
    }

    @Test
    fun `Check Password Uppercase`() {
        // Setup
        val password = "password123!"
        // Test
        val errors = verifyPassword(password)
        // Resultat / Check / Assertion
        assertTrue(errors.contains("Le mot de passe doit contenir au moins une lettre majuscule."))
    }

    @Test
    fun `Check Password Lowercase`() {
        // Setup
        val password = "PASSWORD123!"
        // Test
        val errors = verifyPassword(password)
        // Resultat / Check / Assertion
        assertTrue(errors.contains("Le mot de passe doit contenir au moins une lettre minuscule."))
    }

    @Test
    fun `Check Password Digit`() {
        // Setup
        val password = "Password!"
        // Test
        val errors = verifyPassword(password)
        // Resultat / Check / Assertion
        assertTrue(errors.contains("Le mot de passe doit contenir au moins un chiffre."))
    }

    @Test
    fun `Check Password Special Character`() {
        // Setup
        val password = "Password123"
        // Test
        val errors = verifyPassword(password)
        // Resultat / Check / Assertion
        assertTrue(errors.isEmpty())
    }
}