package cn.imppp.skin.http

class ApiException(var code: Int, override var message: String) : RuntimeException()