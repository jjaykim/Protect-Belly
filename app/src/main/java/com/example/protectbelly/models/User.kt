package com.example.protectbelly.models

class User {
    var name: String? = null;
    var email: String? = null;
    var age: Int? = null;
    var height: Int? = null;
    var weight: Int? = null;
    var phoneNo: String? = null;
    var targetCalories: Int? = null;
    var targetProtein: Int? = null;
    var targetFat: Int? = null;

    constructor(
        name: String?,
        email: String?,
        age: Int?,
        height: Int?,
        weight: Int?,
        phoneNo: String?,
        targetCalories: Int?,
        targetProtein: Int?,
        targetFat: Int?
    ) {
        this.name = name
        this.email = email
        this.age = age
        this.height = height
        this.weight = weight
        this.phoneNo = phoneNo
        this.targetCalories = targetCalories
        this.targetProtein = targetProtein
        this.targetFat = targetFat
    }

    override fun toString(): String {
        return "User(name='$name', email='$email', age=$age, height=$height, weight=$weight, phoneNo=$phoneNo, targetCalories=$targetCalories, targetProtein=$targetProtein, targetFat=$targetFat)"
    }

}