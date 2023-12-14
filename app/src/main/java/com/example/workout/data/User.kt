import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


data class User(
    var firstName: String,
    var lastName: String,
    var dateOfBirth: String,
    val email: String,
    var password: String,
    var isLoggedIn: Boolean
) {

    var goals: String = ""
        set(value: String) {
            field = value
        }
    var poids: Int = 0
        set(value: Int) {
            field = value
        }
    var pas: Int = 0
        set(value: Int) {
            field = value
        }
    var training: String = ""
        set(value: String) {
            field = value
        }

}

class UserManagement(context: Context?) {
    private val sharedPreferences = context?.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val userListKey = "userList"

    fun saveUserList(users: List<User>) {
        val usersJson = gson.toJson(users)
        sharedPreferences?.edit()?.putString(userListKey, usersJson)?.apply()
    }

    fun getUserList(): List<User> {
        val usersJson = sharedPreferences?.getString(userListKey, null)
        return if (usersJson != null) {
            val type = object : TypeToken<List<User>>() {}.type
            gson.fromJson(usersJson, type)
        } else {
            emptyList()
        }
    }

    fun GetUser(mail: String, password: String): User? {
        val users = getUserList()

        for (user in users) {
            if (user.email == mail && user.password == password) {
                user.isLoggedIn = true;
                saveUserList(users)
                return user
            }
        }
        return null

    }
    fun addUser(newUser: User) {
        val users = getUserList().toMutableList()
        users.add(newUser)
        saveUserList(users)
    }
    fun modifyUserByEmail(modifiedUser: User) {
        val users = getUserList().toMutableList()
        val userIndex = users.indexOfFirst { it.email == modifiedUser.email }
        if (userIndex != -1) {
            users[userIndex] = modifiedUser
            saveUserList(users)
        }
    }

    fun logout() {
        val users = getUserList().toMutableList()
        val userIndex = users.indexOfFirst { it.isLoggedIn == true }
        if (userIndex != -1) {
            users[userIndex].isLoggedIn = false
            saveUserList(users)
        }
    }
    fun CurrentUser():User?{
        val users = getUserList()

        for (user in users) {
            if(user.isLoggedIn){
                return user
            }
        }
        return null
    }
    fun GetMails():List<String>
    {
        val users = getUserList()
        val emailList = mutableListOf<String>()

        for (user in users) {
            emailList.add(user.email)
        }

        return emailList
    }

}