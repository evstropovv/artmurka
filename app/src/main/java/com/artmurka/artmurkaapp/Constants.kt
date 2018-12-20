package com.artmurka.artmurkaapp

object Constants {

    init {
        System.loadLibrary("native-lib")
    }
    external fun url(): String
    external fun consumerkey(): String
    external fun consumersecret(): String
    external fun oauthtoken(): String
    external fun oauthtokensecret(): String
    external fun oauthversion(): String
    external fun publicpaykey(): String
    external fun privatepaykey(): String
    external fun applicationemail(): String
    external fun applicationemail_password(): String
    external fun sendtoemail(): String
    external fun loginconsumerkey(): String
    external fun loginconsumersecret(): String
    external fun npapikey(): String
}