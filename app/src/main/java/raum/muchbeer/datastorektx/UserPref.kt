package raum.muchbeer.datastorektx

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "UserPref"

@Singleton
class UserPref (private val context: Context) {


   // private val createKeyStore : DataStore<Preferences> =context.createDataStore("user_pref")
  val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_pref")

    companion object {
       // val PREF_KEYNAME = PrefPreferenceK
        val PREF_KEY_NAME = stringPreferencesKey("user_name")
        val PREF_KEY_AGE = intPreferencesKey("user_age")
        val PREF_KEY_GENDER = booleanPreferencesKey("user_gender")
    }

    suspend fun storeData(username: String, user_age: Int, isFemale: Boolean) {
        context.dataStore.edit {
            it[PREF_KEY_NAME] = username
            it[PREF_KEY_AGE] = user_age
            it[PREF_KEY_GENDER] = isFemale
        }
    }

    val read_username: Flow<String> = context.dataStore.data
        .map {

            it[PREF_KEY_NAME] ?: ""
        }.catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences", exception)
             //   emit(emptyPreferences())
            } else {
                throw exception
            }
        }

    val read_user_age : Flow<Int> = context.dataStore.data
        .map {
            it[PREF_KEY_AGE] ?: 0
        }

    val read_user_gender : Flow<Boolean> = context.dataStore.data
        .map {
            it[PREF_KEY_GENDER] ?: false
        }
}