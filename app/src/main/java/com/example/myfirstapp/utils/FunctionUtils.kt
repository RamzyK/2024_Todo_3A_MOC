package com.example.myfirstapp.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

fun verifyPassword(motDePasse: String): List<String> {
    val erreurs = mutableListOf<String>()

    // Vérifier la longueur minimale
    if (motDePasse.length < 6) {
        erreurs.add("Le mot de passe doit contenir au moins 6 caractères.")
    }

    // Vérifier au moins une lettre majuscule
    if (!Regex("[A-Z]").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins une lettre majuscule.")
    }

    // Vérifier au moins une lettre minuscule
    if (!Regex("[a-z]").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins une lettre minuscule.")
    }

    // Vérifier au moins un chiffre
    if (!Regex("\\d").containsMatchIn(motDePasse)) {
        erreurs.add("Le mot de passe doit contenir au moins un chiffre.")
    }

    // Vérifier au moins un caractère spécial
    val caracteresSpeciaux = "~`!@#\$%\\^&*\\(\\)-_+=<>?/\\[]\\{}|"

    val pattern: Pattern = Pattern.compile(caracteresSpeciaux)
    val matcher: Matcher = pattern.matcher(motDePasse)
    val passwordMatchesqPattern = matcher.matches()
    if (passwordMatchesqPattern) {
        erreurs.add("Le mot de passe doit contenir au moins un caractère spécial parmi $caracteresSpeciaux.")
    }

    return erreurs
}