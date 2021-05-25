package com.example.konturtest.model.local

enum class Temperament{
    MELANCHOLIC,
    PHLEGMATIC,
    SANGUINE,
    CHOLERIC;

    override fun toString(): String {
        return when (this){
            MELANCHOLIC->"melancholic"
            PHLEGMATIC->"phlegmatic"
            SANGUINE->"sanguine"
            CHOLERIC->"choleric"
        }
    }
}