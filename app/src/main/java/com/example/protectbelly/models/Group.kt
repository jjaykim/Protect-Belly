package com.example.protectbelly.models

class Group {
    var groupId: String = "";
    var organizer: String = "";
    var title: String = "";
    var type: String = "";
    var description: String = "";
    var createdAt: String = "";
    var users: ArrayList<String>? = null;
    var documentId: String = ""

    constructor() {}

    constructor(
        groupId: String,
        organizer: String,
        title: String,
        type: String,
        description: String,
        createdAt: String,
        users: ArrayList<String>?,
        documentId: String
    ) {
        this.groupId = groupId
        this.organizer = organizer
        this.title = title
        this.type = type
        this.description = description
        this.createdAt = createdAt
        this.users = users
        this.documentId = documentId
    }
}