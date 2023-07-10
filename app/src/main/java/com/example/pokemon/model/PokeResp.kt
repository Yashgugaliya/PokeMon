package com.example.pokemon.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class PokeResp(
    @SerializedName("data")
    val data: ArrayList<Data>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("totalCount")
    val totalCount: Int
): Parcelable

@Parcelize
data class Attacks(
    @SerializedName("name")
    val name: String,
    @SerializedName("cost")
    val cost: ArrayList<String>,
    @SerializedName("convertedEnergyCost")
    val convertedEnergyCost: Int,
    @SerializedName("damage")
    val damage: String,
    @SerializedName("text")
    val text: String
): Parcelable

@Parcelize
data class Weaknesses(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
): Parcelable

@Parcelize
data class Resistances(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
): Parcelable

@Parcelize
data class Images(
    @SerializedName("small")
    val small: String,
    @SerializedName("large")
    val large: String
): Parcelable

@Parcelize
data class Ability(
    @SerializedName("name")
    val name: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String
): Parcelable

@Parcelize
data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("supertype")
    val supertype: String,
    @SerializedName("subtypes")
    val subtypes: ArrayList<String> = arrayListOf(),
    @SerializedName("level")
    val level: String,
    @SerializedName("hp")
    val hp: String,
    @SerializedName("types")
    val types: ArrayList<String> = arrayListOf(),
    @SerializedName("evolvesFrom")
    val evolvesFrom: String,
    @SerializedName("abilities")
    val abilities: ArrayList<Ability> = arrayListOf(),
    @SerializedName("attacks")
    val attacks: ArrayList<Attacks> = arrayListOf(),
    @SerializedName("weaknesses")
    val weaknesses: ArrayList<Weaknesses> = arrayListOf(),
    @SerializedName("resistances")
    val resistances: ArrayList<Weaknesses> = arrayListOf(),
    @SerializedName("images")
    val images: Images
) : Parcelable


