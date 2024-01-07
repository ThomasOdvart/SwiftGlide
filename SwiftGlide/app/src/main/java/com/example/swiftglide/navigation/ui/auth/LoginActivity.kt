package com.example.swiftglide.navigation.ui.auth

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiftglide.navigation.ui.theme.SwiftGlideTheme
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swiftglide.R
import com.example.swiftglide.navigation.Screen
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun ForgotPasswordText() {
    Row(

    ) {
        Text(
            text = "Forgot ",
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
        Text(
            text = "username ",
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF54BE70),
        )
        Text(
            text = "or ",
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
        Text(
            text = "password?",
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF54BE70),
        )
    }
}

@Composable
fun SwitchToSignupText(navController: NavController) {
    Row(
        modifier = Modifier
            .clickable {
                navController.navigate(Screen.SignupScreen.route)
            }
    ) {
        Text(
            text = "Want to start an ",
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
        Text(
            text = "organization?",
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF54BE70),
        )
    }
}

/**
 * Adds the user to the database and navigates back.
 *
 * @param navController The navigation controller.
 * @param user The user to be added.
 * @param homeViewModel The HomeViewModel.
 */

private fun addUserInDB(
    navController: NavController,
    user: User,
    homeViewModel: HomeViewModel
) {
    homeViewModel.addUser(user)
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.full_logo),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(219.dp)
    )
}



/**
 * Composable function for the vertical layout of the login screen.
 *
 * @param navController The navigation controller.
 * @param authViewModel The AuthViewModel.
 * @param homeViewModel The HomeViewModel.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenVertical(navController: NavController, authViewModel : AuthViewModel, homeViewModel: HomeViewModel) {

    var email by remember { mutableStateOf("") }
    var isEmailEmpty by remember { mutableStateOf(false) }
    var isEmailWrongFormat by remember { mutableStateOf(false) }
    var isEmailTooLong by remember { mutableStateOf(false) }
    var hasEmailInvalidDomain by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var isPasswordEmpty by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }
    var loginFailed by remember { mutableStateOf(false) }

    var loginresponse by remember { mutableStateOf(LoginResponse(false, "", "")) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    0.0f to Color(0xFF476370),
                    500.0f to Color(0xFF163949),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    ) {
        Spacer(modifier = Modifier.height(75.dp))

        Logo()

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.wrapContentSize(),
        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 50.dp)
                    .border(
                        1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .background(Color(22, 57, 73)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_avatar),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            TextField(
                value = email,
                onValueChange = {
                    email = it
                    isEmailWrongFormat = false
                    isEmailEmpty = false
                    isEmailTooLong = false
                    hasEmailInvalidDomain = false
                    loginFailed = false},
                label = null,
                placeholder = {
                    Text(
                        text = "Email",
                        fontSize = 15.sp,
                        color = Color.White,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(22, 57, 73),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                modifier = Modifier
                    .height(50.dp)
                    .border(
                        1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    ),
                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
            )
        }

        if (isEmailEmpty) {
            Text(text = "Email must not be empty", color = Color.Red)
        }
        if (isEmailWrongFormat) {
            Text(text = "Format must be user@example.com", color = Color.Red)
        }
        if (isEmailTooLong) {
            Text(text = "Email is too long", color = Color.Red)
        }
        if (hasEmailInvalidDomain) {
            Text(text = "Invalid domain", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.wrapContentSize(),
        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 50.dp)
                    .border(
                        1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .background(Color(22, 57, 73)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_lock),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            TextField(
                value = password,
                onValueChange = {
                    password = it
                    isPasswordEmpty = false
                    loginFailed = false},
                label = null,
                placeholder = {
                    Text(
                        text = "Password",
                        color = Color.White,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(22, 57, 73),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                modifier = Modifier
                    .height(50.dp)
                    .border(
                        1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    )
                    .padding(0.dp),
                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
            )
        }

        if (isPasswordEmpty) {
            Text(text = "Password must not be empty", color = Color.Red)
        }

        if (loginFailed) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (email.isNullOrEmpty()) {
                    isEmailEmpty = true
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    isEmailWrongFormat = true
                } else if (email.length < 5 || email.length > 50) {
                    isEmailTooLong = true
                } else if (email.split(".").last().length !in 2..6) {
                    hasEmailInvalidDomain = true
                }

                if (password.isNullOrEmpty()) {
                    isPasswordEmpty = true
                }

                if(!isEmailEmpty && !isEmailWrongFormat && !isEmailTooLong && !hasEmailInvalidDomain && !isPasswordEmpty) {

                    coroutineScope.launch {
                        authViewModel.login(email, password)

                        delay(2000)

                        authViewModel.loginResponse.collect { loginResponse ->

                            if(loginResponse.validated) {

                                val user = User(email, loginResponse.role)

                                addUserInDB(navController, user, homeViewModel)
                                delay(1000)
                                Log.d("database", "LoginScreen: added user to db")
                                navController.navigate(Screen.HomeScreen.route)
                            } else {
                                errorMessage = loginResponse.message
                                loginFailed = true
                            }

                        }
                    }



                }
            },
            modifier = Modifier
                .width(275.dp)
                .height(50.dp)
                .background(
                    color = Color(84, 190, 112),
                    shape = RoundedCornerShape(40.dp)
                ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            /*elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)*/
        ) {
            Text(
                text = "LOGIN",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        ForgotPasswordText()

        Spacer(modifier = Modifier.weight(1f))

        SwitchToSignupText(navController)

        Spacer(modifier = Modifier.height(10.dp))
    }

}

/**
 * Composable function for the landscape layout of the login screen.
 *
 * @param navController The navigation controller.
 * @param authViewModel The AuthViewModel.
 * @param homeViewModel The HomeViewModel.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenLandscape(navController: NavController, authViewModel : AuthViewModel, homeViewModel: HomeViewModel) {

    var email by remember { mutableStateOf("") }
    var isEmailEmpty by remember { mutableStateOf(false) }
    var isEmailWrongFormat by remember { mutableStateOf(false) }
    var isEmailTooLong by remember { mutableStateOf(false) }
    var hasEmailInvalidDomain by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var isPasswordEmpty by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }
    var loginFailed by remember { mutableStateOf(false) }

    var loginresponse by remember { mutableStateOf(LoginResponse(false, "", "")) }

    val coroutineScope = rememberCoroutineScope()

    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    0.0f to Color(0xFF476370),
                    500.0f to Color(0xFF163949),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .padding(horizontal = if (isLandscape) 16.dp else 0.dp) // Adjust padding for landscape
    ) {
        Spacer(modifier = Modifier.height(75.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier
                .weight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.full_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(219.dp)
                )
            }

            Box(
                modifier = Modifier.weight(1f),
            ) {

                Column() {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                    ) {

                        Box(
                            modifier = Modifier
                                .size(width = 40.dp, height = 50.dp)
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(
                                        topStart = 10.dp,
                                        bottomStart = 10.dp
                                    )
                                )
                                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                                .background(Color(22, 57, 73)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_avatar),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        TextField(
                            value = email,
                            onValueChange = {
                                email = it
                                isEmailWrongFormat = false
                                isEmailEmpty = false
                                isEmailTooLong = false
                                hasEmailInvalidDomain = false
                                loginFailed = false
                            },
                            label = null,
                            placeholder = {
                                Text(
                                    text = "Email",
                                    fontSize = 15.sp,
                                    color = Color.White,
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(22, 57, 73),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                            modifier = Modifier
                                .height(50.dp)
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                                ),
                            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                        )
                    }

                    if (isEmailEmpty) {
                        Text(text = "Email must not be empty", color = Color.Red)
                    }
                    if (isEmailWrongFormat) {
                        Text(text = "Format must be user@example.com", color = Color.Red)
                    }
                    if (isEmailTooLong) {
                        Text(text = "Email is too long", color = Color.Red)
                    }
                    if (hasEmailInvalidDomain) {
                        Text(text = "Invalid domain", color = Color.Red)
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 40.dp, height = 50.dp)
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(
                                        topStart = 10.dp,
                                        bottomStart = 10.dp
                                    )
                                )
                                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                                .background(Color(22, 57, 73)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_lock),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                                isPasswordEmpty = false
                                loginFailed = false
                            },
                            label = null,
                            placeholder = {
                                Text(
                                    text = "Password",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(22, 57, 73),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                            modifier = Modifier
                                .height(50.dp)
                                .border(
                                    1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                                )
                                .padding(0.dp),
                            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                        )
                    }

                    if (isPasswordEmpty) {
                        Text(text = "Password must not be empty", color = Color.Red)
                    }

                    if (loginFailed) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = errorMessage, color = Color.Red)
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier
                            .wrapContentSize(),
                    ) {
                        Button(
                            onClick = {
                                if (email.isNullOrEmpty()) {
                                    isEmailEmpty = true
                                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                    isEmailWrongFormat = true
                                } else if (email.length < 5 || email.length > 50) {
                                    isEmailTooLong = true
                                } else if (email.split(".").last().length !in 2..6) {
                                    hasEmailInvalidDomain = true
                                }

                                if (password.isNullOrEmpty()) {
                                    isPasswordEmpty = true
                                }

                                if (!isEmailEmpty && !isEmailWrongFormat && !isEmailTooLong && !hasEmailInvalidDomain && !isPasswordEmpty) {

                                    coroutineScope.launch {
                                        authViewModel.login(email, password)

                                        delay(2000)

                                        authViewModel.loginResponse.collect { loginResponse ->

                                            if (loginResponse.validated) {

                                                val user = User(email, loginResponse.role)

                                                addUserInDB(navController, user, homeViewModel)
                                                delay(1000)
                                                Log.d("database", "LoginScreen: added user to db")
                                                navController.navigate(Screen.HomeScreen.route)
                                            } else {
                                                errorMessage = loginResponse.message
                                                loginFailed = true
                                            }

                                        }
                                    }
                                }
                            },
                            modifier = Modifier
                                .width(275.dp)
                                .height(50.dp)
                                .background(
                                    color = Color(84, 190, 112),
                                    shape = RoundedCornerShape(40.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        ) {
                            Text(
                                text = "LOGIN",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        ForgotPasswordText()

        Spacer(modifier = Modifier.weight(1f))

        SwitchToSignupText(navController)

        Spacer(modifier = Modifier.height(10.dp))

    }
}

/**
 * Main entry point for the Login screen.
 *
 * @param navController The navigation controller.
 * @param authViewModel The AuthViewModel.
 * @param homeViewModel The HomeViewModel.
 */
@Composable
fun LoginScreen(navController: NavController, authViewModel : AuthViewModel, homeViewModel: HomeViewModel) {

    var isLandscape by remember { mutableStateOf(false) }
    
    // Observe the current configuration
    val configuration = LocalConfiguration.current
    val newIsLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape != newIsLandscape) {
        // Orientation changed, do something here if needed
        // You may want to update UI or take specific actions
        isLandscape = newIsLandscape
    }

    // Call the appropriate UI function based on the orientation
    if (isLandscape) {
        LoginScreenLandscape(
            navController = navController,
            authViewModel = authViewModel,
            homeViewModel = homeViewModel
        )
    } else {
        LoginScreenVertical(
            navController = navController,
            authViewModel = authViewModel,
            homeViewModel = homeViewModel
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput() {
    var email by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }
    var isEmpty by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.wrapContentSize(),
    ) {
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 50.dp)
                .border(
                    1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                )
                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                .background(Color(22, 57, 73)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_avatar),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        TextField(
            value = email,
            onValueChange = { input ->
                email = input
                isEmpty = input.isEmpty()
                isValid = isEmpty},
            label = null,
            isError = !isValid,
            placeholder = {
                Text(
                    text = "Email",
                    fontSize = 15.sp,
                    color = Color.White,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(22, 57, 73),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier
                .height(50.dp)
                .border(
                    1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                ),
            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
        )

    }
    if (!isEmpty) {
        Text(text = "Email cannot be empty", color = Color.Red)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput() {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.wrapContentSize(),
    ) {
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 50.dp)
                .border(
                    1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
                )
                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                .background(Color(22, 57, 73)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_lock),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = null,
            placeholder = {
                Text(
                    text = "Password",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(22, 57, 73),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier
                .height(50.dp)
                .border(
                    1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                )
                .padding(0.dp),
            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
        )
    }
}


@Composable
fun LoginButton(navController: NavController) {
    Button(
        onClick = {

        },
        modifier = Modifier
            .width(275.dp)
            .height(50.dp)
            .background(
                color = Color(84, 190, 112),
                shape = RoundedCornerShape(40.dp)
            ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        /*elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)*/
    ) {
        Text(
            text = "LOGIN",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

/*@Composable
@Preview
fun LoginScreenPreview() {
    // Call your LoginScreen composable with some default values or mocks
    // Example:
    LoginScreen(navController = rememberNavController())
}*/

