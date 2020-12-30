package cn.imppp.skin.repository.net

import android.content.Context
import androidx.datastore.CorruptionException
import androidx.datastore.DataStore
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import cn.imppp.skin.PersonProtos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class ProtoDataStoreRepository(val context: Context) {

    private val FILE_NAME = "ProtoDataStore.pb"

    var protoDataStore: DataStore<PersonProtos.Person> =
        context.createDataStore(fileName = FILE_NAME, serializer = PersonSerializer)

    suspend fun saveData(personModel: String) {
        protoDataStore.updateData { person ->
            person.toBuilder().setName(personModel).build()
        }
    }

    fun readData(): Flow<PersonProtos.Person> {
        return protoDataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(PersonProtos.Person.getDefaultInstance())
                } else {
                    throw it
                }
            }
    }

}

object PersonSerializer : Serializer<PersonProtos.Person> {
    override fun readFrom(input: InputStream): PersonProtos.Person {
        try {
            return PersonProtos.Person.parseFrom(input) // 是编译器自动生成的，用于读取并解析 input 的消息
        } catch (exception: Exception) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: PersonProtos.Person, output: OutputStream) =
        t.writeTo(output) // t.writeTo(output) 是编译器自动生成的，用于写入序列化消息
}


