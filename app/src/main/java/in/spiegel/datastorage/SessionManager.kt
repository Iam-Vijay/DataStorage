package `in`.spiegel.datastorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Vijay on 12/6/2021.
 */
class SessionManager @Inject constructor(@ApplicationContext val context: Context) {

    // private val applicationcontext = context.applicationContext
    private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "sessionmanager")

    companion object{
        val Name  = stringPreferencesKey("name")
        val Age = intPreferencesKey("age")
        val Pass = booleanPreferencesKey("ispass")
    }

    suspend fun savetodataStore(name:String,age:Int,ispass:Boolean){
        context.dataStore.edit {
            it[Name] = name
            it[Age] = age
            it[Pass] = ispass
        }
    }

    val exampleCounterFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[Name].toString() ?: "0"
        }


}