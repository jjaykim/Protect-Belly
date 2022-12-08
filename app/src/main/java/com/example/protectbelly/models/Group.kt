package com.example.protectbelly.models

import java.io.Serializable

class Group : Serializable, Any {
    var organizerId: String = "";
    var organizerName: String = "";
    var title: String = "";
    var type: String = "";
    var description: String = "";
    var createdAt: String = "";
    var users: ArrayList<String>? = null;
    var documentId: String = ""
    var logo: Int = 0;
    var location: String = "";

    constructor() {}

    constructor(group: Group) {
        this.organizerId = group.organizerId
        this.organizerName = group.organizerName
        this.title = group.title
        this.type = group.type
        this.description = group.description
        this.createdAt = group.createdAt
        this.users = group.users
        this.documentId = group.documentId
        this.logo = group.logo
        this.location = group.location
    }

    constructor(
        organizerId: String,
        organizerName: String,
        title: String,
        type: String,
        description: String,
        createdAt: String,
        users: ArrayList<String>?,
        documentId: String,
        logo: Int,
        location: String
    ) {
        this.organizerId = organizerId
        this.organizerName = organizerName
        this.title = title
        this.type = type
        this.description = description
        this.createdAt = createdAt
        this.users = users
        this.documentId = documentId
        this.logo = logo
        this.location = location
    }

    override fun toString(): String {
        return "Group(documentId=$documentId organizerId=$organizerId, organizerName=$organizerName, title=$title, type=$type, description=$description, createdAt=$createdAt, location=$location users=$users)"
    }
}