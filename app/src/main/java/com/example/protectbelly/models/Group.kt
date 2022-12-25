package com.example.protectbelly.models

class Group {
    var organizerId: String = "";
    var organizerName: String = "";
    var title: String = "";
    var type: String = "";
    var description: String = "";
    var createdAt: String = "";
    var users: ArrayList<String>? = null;
    var documentId: String = ""
    var logo: Int = 0;

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
        logo: Int
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
    }

    override fun toString(): String {
        return "Group(organizerId=$organizerId, organizerName=$organizerName, title=$title, type=$type, description=$description, createdAt=$createdAt, users=$users)"
    }
}