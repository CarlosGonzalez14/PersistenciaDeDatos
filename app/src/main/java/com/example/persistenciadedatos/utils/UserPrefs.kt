import android.content.Context

object UserPrefs {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_USERNAME = "username"

    fun saveUsername(context: Context, username: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_USERNAME, username)
            .apply()
    }

    fun getUsername(context: Context): String? {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_USERNAME, null)
    }
}