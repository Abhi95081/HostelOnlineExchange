package com.example.clx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthviewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    private val _authState = MutableLiveData<AuthState>()
    val authState: MutableLiveData<AuthState> = _authState



    init {
        checkAuthState()
    }

    fun checkAuthState() {
        if (auth.currentUser == null) {
            _isUserLoggedIn.value = false
            _authState.value = AuthState.Unauthenticated
        } else {
            _isUserLoggedIn.value = true
            _authState.value = AuthState.Authenticated
        }
    }

    fun UserLogin(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and Password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isUserLoggedIn.value = true
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signup(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            _authState.value = AuthState.Error("Email and Password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isUserLoggedIn.value = true
                    _authState.value = AuthState.Authenticated

                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signOut() {
        _authState.value = AuthState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            delay(1500) // Simulate a delay for UX (optional)
            auth.signOut()
            _isUserLoggedIn.value = false
            _authState.postValue(AuthState.Unauthenticated)
        }
    }



}

sealed class AuthState {
    object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    class Error(val message: String) : AuthState()

}