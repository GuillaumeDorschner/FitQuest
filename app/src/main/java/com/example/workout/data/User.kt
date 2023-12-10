import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class User(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val email: String,
    val password: String
)

class UserManagement(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val userListKey = "userList"

    fun saveUserList(users: List<User>) {
        val usersJson = gson.toJson(users)
        sharedPreferences.edit().putString(userListKey, usersJson).apply()
    }

    fun getUserList(): List<User> {
        val usersJson = sharedPreferences.getString(userListKey, null)
        return if (usersJson != null) {
            val type = object : TypeToken<List<User>>() {}.type
            gson.fromJson(usersJson, type)
        } else {
            emptyList()
        }
    }

    fun addUser(newUser: User) {
        val users = getUserList().toMutableList()
        users.add(newUser)
        saveUserList(users)
    }
}