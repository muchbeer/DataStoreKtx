package raum.muchbeer.datastorektx

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserPrefViewModel (private val userPref: UserPref) : ViewModel() {

  //  private  var _username_edtText = MutableLiveData<String>()
    val usernameEdt = MutableLiveData<String>()
  //      get() = _username_edtText

  //  private  var _age_edtText = MutableLiveData<Int>()
    val ageEdt = MutableLiveData<String>()
     //   get() = _age_edtText


  //  private var _user_checkGender = MutableLiveData<Boolean>()
    val user_checkGender =MutableLiveData<Boolean>()
      //      get() = _user_checkGender
 init {
     usernameEdt.value=""
          ageEdt.value="0"
          user_checkGender.value=true
 }

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

   // val read_username = userPref.read_username
 //   val read_userAge = userPref.read_user_age
   // val read_gender = userPref.read_user_gender

    val read_username_VM = userPref.read_username.asLiveData(
        Dispatchers.Default + viewModelScope.coroutineContext
    )

    val read_userAgeVM = userPref.read_user_age.asLiveData(
        Dispatchers.Default + viewModelScope.coroutineContext
    )

    val read_gender = userPref.read_user_gender.asLiveData(
        Dispatchers.Default + viewModelScope.coroutineContext
    )
    val read_username_VMString = Transformations.map(read_userAgeVM) { ageInt->
        ageInt.toString()
    }

    val read_userGenderConvert  = Transformations.map(read_gender) { isFemale ->
        if (isFemale) "Female" else "Male"
    }

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