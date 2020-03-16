package com.example.listview

class Sheet {

    var topic:String?=null
    var title:String?=null
    var content:String?=null
    var source:String?=null
    var img:Int?=null

    constructor(a:String, b:String, c:String, d:String, e:Int)
    {
        this.topic = a
        this.title = b
        this.content = c
        this.source = d
        this.img = e
    }
}