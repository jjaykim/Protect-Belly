package com.example.protectbelly.models

class User {
    var name: String? = null;
    var email: String? = null;
    var age: Int? = null;
    var gender: String? = null;
    var height: Int? = null;
    var weight: Int? = null;
    var phoneNo: String? = null;
    var targetCalories: Int? = null;
    var targetCarbs: Int? = null;
    var targetProtein: Int? = null;
    var targetFat: Int? = null;

    constructor() {

    }

    constructor(
        name: String?,
        email: String?,
        age: Int?,
        gender: String?,
        height: Int?,
        weight: Int?,
        phoneNo: String?,
        targetCalories: Int?,
        targetCarbs: Int?,
        targetProtein: Int?,
        targetFat: Int?
    ) {
        this.name = name
        this.email = email
        this.age = age
        this.gender = gender
        this.height = height
        this.weight = weight
        this.phoneNo = phoneNo
        this.targetCalories = targetCalories
        this.targetCarbs = targetCarbs
        this.targetProtein = targetProtein
        this.targetFat = targetFat
    }

    override fun toString(): String {
        return "User(name=$name, email=$email, age=$age, gender=$gender, height=$height, weight=$weight, phoneNo=$phoneNo, targetCalories=$targetCalories, targetCarbs=$targetCarbs, targetProtein=$targetProtein, targetFat=$targetFat)"
    }


}