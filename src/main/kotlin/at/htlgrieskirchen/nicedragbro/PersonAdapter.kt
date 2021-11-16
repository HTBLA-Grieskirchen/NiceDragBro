package at.htlgrieskirchen.nicedragbro

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class PersonAdapter : TypeAdapter<Person>() {
    private val gson = Gson()
    override fun write(writer: JsonWriter, value: Person?) {
        if (value != null) gson.toJson(PersonBuilder(value), PersonBuilder::class.java, writer)
        else writer.nullValue()
    }

    override fun read(reader: JsonReader): Person {
        return gson.fromJson<PersonBuilder>(reader, PersonBuilder::class.java).build()
    }
}