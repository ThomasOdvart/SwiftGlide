package com.example.swiftglide.navigation.ui.auth

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.runtime.collectAsState
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swiftglide.R
import com.example.swiftglide.navigation.Screen
import com.example.swiftglide.navigation.data.model.SignupResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignupScreenVertical(navController: NavController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var organizationName by remember { mutableStateOf("") }

    var emailErrorText by remember { mutableStateOf("") }
    var passwordErrorText by remember { mutableStateOf("") }
    var confirmPasswordErrorText by remember { mutableStateOf("") }
    var organizationNameErrorText by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }
    var loginFailed by remember { mutableStateOf(false) }
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

        //Email input
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

            val containerColor = Color(22, 57, 73)
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    emailErrorText = ""
                },
                label = null,
                placeholder = {
                    Text(
                        text = "Email",
                        fontSize = 15.sp,
                        color = Color.White,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
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
        if (!emailErrorText.isEmpty()) {
            Text(text = emailErrorText, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Password input
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

            val containerColor = Color(22, 57, 73)
            TextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordErrorText = "" },
                label = null,
                placeholder = {
                    Text(
                        text = "Password",
                        color = Color.White,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
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

        if (!passwordErrorText.isEmpty()) {
            Text(text = passwordErrorText, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Confirm password input
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

            val containerColor = Color(22, 57, 73)
            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordErrorText = "" },
                label = null,
                placeholder = {
                    Text(
                        text = "Confirm password",
                        color = Color.White,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Visible,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
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

        if (!confirmPasswordErrorText.isEmpty()) {
            Text(text = confirmPasswordErrorText, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))


        // Organization name input
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
                    painter = painterResource(id = R.drawable.icon_organization),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            val containerColor = Color(22, 57, 73)
            TextField(
                value = organizationName,
                onValueChange = {
                    organizationName = it
                    organizationNameErrorText = "" },
                label = null,
                placeholder = {
                    Text(
                        text = "Organization name",
                        color = Color.White,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Visible,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
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

        if (!organizationNameErrorText.isEmpty()) {
            Text(text = organizationNameErrorText, color = Color.Red)
        }

        if (loginFailed) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (email.isEmpty()) {
                    emailErrorText = "Email must not be empty"
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailErrorText = "Format must be user@example.com"
                } else if (email.length < 5 || email.length > 50) {
                    emailErrorText = "Email is too long"
                } else if (email.split(".").last().length !in 2..6) {
                    emailErrorText = "Invalid domain"
                }

                if (password.isEmpty()) {
                    passwordErrorText = "Password must not be empty"
                } else if (confirmPassword.isEmpty()) {
                    confirmPasswordErrorText = "Confirm password must not be empty"
                } else {
                    if (password != confirmPassword) {
                        confirmPasswordErrorText = "Passwords do not match"
                    }
                }

                if (organizationName.isEmpty()) {
                    organizationNameErrorText = "Organization name must not be empty"
                }

                if (emailErrorText.isEmpty() && passwordErrorText.isEmpty() && confirmPasswordErrorText.isEmpty() && organizationNameErrorText.isEmpty()) {

                    coroutineScope.launch {
                        authViewModel.signup(email, password, organizationName)
                        Log.d("lauch", "authviewmodel called")

                        delay(2000)

                        authViewModel.signupResponse.collect { signupResponse ->
                            Log.d("response", "Signupscreen: $signupResponse")
                            if (signupResponse.message.isNotEmpty()) {
                                if(signupResponse.validated) {
                                    navController.navigate(Screen.HomeScreen.route)
                                } else {
                                    errorMessage = signupResponse.message
                                    loginFailed = true
                                }
                            }
                        }

                    }
                    /*
                    coroutineScope.launch {
                        authViewModel.signup(email, password, organizationName)


                        authViewModel.signupResponse.collectLatest { signupResponse ->
                            Log.d("response", "Signupscreen: $signupResponse")
                            if (signupResponse.message.isNotEmpty()) {
                                if(signupResponse.validated) {
                                    navController.navigate(Screen.HomeScreen.route)
                                } else {
                                    errorMessage = signupResponse.message
                                    loginFailed = true
                                }
                            }
                        }
                    }*/
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
                text = "SIGNUP",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SignupSwitchToSignupText(navController)

        Spacer(modifier = Modifier.height(10.dp))
    }
}
@Composable
fun SignupScreenLandscape(navController: NavController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var organizationName by remember { mutableStateOf("") }

    var emailErrorText by remember { mutableStateOf("") }
    var passwordErrorText by remember { mutableStateOf("") }
    var confirmPasswordErrorText by remember { mutableStateOf("") }
    var organizationNameErrorText by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }
    var loginFailed by remember { mutableStateOf(false) }
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

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier
                .weight(1f)
            ) {
                Logo()
            }

            Box(modifier = Modifier
                .weight(3f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    //email pass column
                    Column {
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

                            val containerColor = Color(22, 57, 73)
                            TextField(
                                value = email,
                                onValueChange = {
                                    email = it
                                    emailErrorText = ""
                                },
                                label = null,
                                placeholder = {
                                    Text(
                                        text = "Email",
                                        fontSize = 15.sp,
                                        color = Color.White,
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = containerColor,
                                    unfocusedContainerColor = containerColor,
                                    disabledContainerColor = containerColor,
                                    cursorColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
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
                        if (!emailErrorText.isEmpty()) {
                            Text(text = emailErrorText, color = Color.Red)
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

                            val containerColor = Color(22, 57, 73)
                            TextField(
                                value = password,
                                onValueChange = {
                                    password = it
                                    passwordErrorText = "" },
                                label = null,
                                placeholder = {
                                    Text(
                                        text = "Password",
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center,
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = containerColor,
                                    unfocusedContainerColor = containerColor,
                                    disabledContainerColor = containerColor,
                                    cursorColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
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

                        if (!passwordErrorText.isEmpty()) {
                            Text(text = passwordErrorText, color = Color.Red)
                        }

                    }
                    //confirm org column
                    Column {
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

                            val containerColor = Color(22, 57, 73)
                            TextField(
                                value = confirmPassword,
                                onValueChange = {
                                    confirmPassword = it
                                    confirmPasswordErrorText = "" },
                                label = null,
                                placeholder = {
                                    Text(
                                        text = "Confirm password",
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center,
                                        overflow = TextOverflow.Visible,
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = containerColor,
                                    unfocusedContainerColor = containerColor,
                                    disabledContainerColor = containerColor,
                                    cursorColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
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

                        if (!confirmPasswordErrorText.isEmpty()) {
                            Text(text = confirmPasswordErrorText, color = Color.Red)
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
                                    painter = painterResource(id = R.drawable.icon_organization),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            val containerColor = Color(22, 57, 73)
                            TextField(
                                value = organizationName,
                                onValueChange = {
                                    organizationName = it
                                    organizationNameErrorText = "" },
                                label = null,
                                placeholder = {
                                    Text(
                                        text = "Organization name",
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center,
                                        overflow = TextOverflow.Visible,
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = containerColor,
                                    unfocusedContainerColor = containerColor,
                                    disabledContainerColor = containerColor,
                                    cursorColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
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

                        if (!organizationNameErrorText.isEmpty()) {
                            Text(text = organizationNameErrorText, color = Color.Red)
                        }

                        if (loginFailed) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = errorMessage, color = Color.Red)
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                if (email.isEmpty()) {
                    emailErrorText = "Email must not be empty"
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailErrorText = "Format must be user@example.com"
                } else if (email.length < 5 || email.length > 50) {
                    emailErrorText = "Email is too long"
                } else if (email.split(".").last().length !in 2..6) {
                    emailErrorText = "Invalid domain"
                }

                if (password.isEmpty()) {
                    passwordErrorText = "Password must not be empty"
                } else if (confirmPassword.isEmpty()) {
                    confirmPasswordErrorText = "Confirm password must not be empty"
                } else {
                    if (password != confirmPassword) {
                        confirmPasswordErrorText = "Passwords do not match"
                    }
                }

                if (organizationName.isEmpty()) {
                    organizationNameErrorText = "Organization name must not be empty"
                }

                if (emailErrorText.isEmpty() && passwordErrorText.isEmpty() && confirmPasswordErrorText.isEmpty() && organizationNameErrorText.isEmpty()) {

                    coroutineScope.launch {
                        authViewModel.signup(email, password, organizationName)
                        Log.d("lauch", "authviewmodel called")

                        delay(2000)

                        authViewModel.signupResponse.collect { signupResponse ->
                            Log.d("response", "Signupscreen: $signupResponse")
                            if (signupResponse.message.isNotEmpty()) {
                                if(signupResponse.validated) {
                                    navController.navigate(Screen.HomeScreen.route)
                                } else {
                                    errorMessage = signupResponse.message
                                    loginFailed = true
                                }
                            }
                        }

                    }
                    /*
                    coroutineScope.launch {
                        authViewModel.signup(email, password, organizationName)


                        authViewModel.signupResponse.collectLatest { signupResponse ->
                            Log.d("response", "Signupscreen: $signupResponse")
                            if (signupResponse.message.isNotEmpty()) {
                                if(signupResponse.validated) {
                                    navController.navigate(Screen.HomeScreen.route)
                                } else {
                                    errorMessage = signupResponse.message
                                    loginFailed = true
                                }
                            }
                        }
                    }*/
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
                text = "SIGNUP",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SignupSwitchToSignupText(navController)

        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
fun SignupScreen(navController: NavController, authViewModel: AuthViewModel) {

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
        SignupScreenLandscape(
            navController = navController,
            authViewModel = authViewModel
        )
    } else {
        SignupScreenVertical(
            navController = navController,
            authViewModel = authViewModel
        )
    }
}

@Composable
fun SignupSwitchToSignupText(navController: NavController) {
    Row(
        modifier = Modifier
            .clickable {
                navController.navigate(Screen.LoginScreen.route)
            }
    ) {
        Text(
            text = "Already have an ",
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
        Text(
            text = "account?",
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF54BE70),
        )
    }
}

/*@Composable
@Preview
fun SignupScreenPreview() {
    // Call your LoginScreen composable with some default values or mocks
    // Example:
    SignupScreen(navController = rememberNavController())
}*/