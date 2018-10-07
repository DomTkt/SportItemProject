package com.example.dom.firstkotlinproject.models


data class SportItemLocation(
    val type: String,
    val crs: Crs,
    val features: List<Feature>
)

data class Feature(
    val type: String,
    val propertie: Propertie,
    val geometry: Geometry
)

data class Propertie(
    val gml_id: String,
    val nom: String,
    val theme: String,
    val soustheme: String,
    val identifiant: String,
    val idexterne: String,
    val siret: Any,
    val datecreation: String,
    val gid: String
)

data class Geometry(
    val type: String,
    val coordinates: List<List<List<Double>>>
)

data class Crs(
    val type: String,
    val properties: Properties
)

data class Properties(
    val name: String
)