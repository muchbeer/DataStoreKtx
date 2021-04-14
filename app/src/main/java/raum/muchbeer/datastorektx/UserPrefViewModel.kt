package raum.muchbeer.datastorektx

import androidx.lifecycle.*
import kotlinx.coroutines.launch


class UserPrefViewModel (private val userPref: UserPref) : ViewModel() {

  //  private  var _username_edtText = MutableLiveData<String>()
    val usernameEdt = MutableLiveData<String>()
  //      get() = _username_edtText

  //  private  var _age_edtText = MutableLiveData<Int>()
    val ageEdt = MutableLiveData<String>()
     //   get() = _age_edtText

    val ageInString = Transformations.map(ageEdt) { ageInt->
        ageInt.toString()
    }
  //  private var _user_checkGender = MutableLiveData<Boolean>()
    val user_checkGender =MutableLiveData<Boolean>()
      //      get() = _user_checkGender

    fun checkGender(isFemale: Boolean) {
        if (isFemale) {
            user_checkGender.value = true
        } else {
            user_checkGender.value = false
        }
    }
fun saveUserData() = viewModelScope.launch {
    userPref.storeData(usernameEdt.value.toString(), ageEdt.value!!.toInt(), user_checkGender.value!!)
}

    val read_username = userPref.read_username
    val read_userAge = userPref.read_user_age
    val read_gender = userPref.read_user_gender


}

class UserPrefViewModelFactory(private val userPref: UserPref) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserPrefViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserPrefViewModel(userPref) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")

    }
}