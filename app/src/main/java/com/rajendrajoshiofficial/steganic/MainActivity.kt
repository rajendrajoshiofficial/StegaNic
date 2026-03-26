package com.rajendrajoshiofficial.steganic

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import android.net.Uri
import android.content.Intent
import android.os.Build
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rajendrajoshiofficial.steganic.ui.screens.HomeScreen
import com.rajendrajoshiofficial.steganic.ui.screens.OnboardingScreen
import com.rajendrajoshiofficial.steganic.ui.theme.SteganoCryxTheme
import com.rajendrajoshiofficial.steganic.viewmodel.SteganoViewModel
import com.rajendrajoshiofficial.steganic.viewmodel.ThemeViewModel
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        // SECURITY: Prevent screenshots, screen recording, and recent-apps snapshot
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        val prefs = getSharedPreferences("steganic_fast_prefs", android.content.Context.MODE_PRIVATE)
        val isFirstLaunch = prefs.getBoolean("is_first_launch", true)
        val isAppLockEnabled = prefs.getBoolean("is_app_lock_enabled", false)
        
        val sharedUri: Uri? = if (intent?.action == Intent.ACTION_SEND) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent?.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
            }
        } else null

        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            var isAuthenticated by remember { mutableStateOf(!isAppLockEnabled) }

            val lifecycleOwner = LocalLifecycleOwner.current
            var lifecycleState by remember { mutableStateOf(Lifecycle.State.INITIALIZED) }

            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    lifecycleState = event.targetState
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }

            LaunchedEffect(lifecycleState, isAuthenticated) {
                if (isAppLockEnabled && !isAuthenticated && lifecycleState == Lifecycle.State.RESUMED) {
                    val biometricManager = BiometricManager.from(this@MainActivity)
                    val allowedAuth = BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                    if (biometricManager.canAuthenticate(allowedAuth) == BiometricManager.BIOMETRIC_SUCCESS) {
                        val executor = ContextCompat.getMainExecutor(this@MainActivity)
                        val biometricPrompt = BiometricPrompt(this@MainActivity, executor,
                            object : BiometricPrompt.AuthenticationCallback() {
                                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                                    super.onAuthenticationSucceeded(result)
                                    isAuthenticated = true
                                }
                                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                                    super.onAuthenticationError(errorCode, errString)
                                    if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                                        finish() 
                                    }
                                }
                            })
                        val promptInfo = BiometricPrompt.PromptInfo.Builder()
                            .setTitle("StegaNic Vault Locked")
                            .setSubtitle("Authenticate to access your secrets")
                            .setAllowedAuthenticators(allowedAuth)
                            .build()
                        biometricPrompt.authenticate(promptInfo)
                    } else {
                        isAuthenticated = true 
                    }
                }
            }

            SteganoCryxTheme(
                useDarkTheme = isDarkMode ?: androidx.compose.foundation.isSystemInDarkTheme()
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (!isAuthenticated) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Default.Lock, contentDescription = "Locked", modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Vault is Locked", style = MaterialTheme.typography.titleLarge)
                            Spacer(modifier = Modifier.height(32.dp))
                            Button(onClick = { 
                                val biometricManager = BiometricManager.from(this@MainActivity)
                                val allowedAuth = BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                                if (biometricManager.canAuthenticate(allowedAuth) == BiometricManager.BIOMETRIC_SUCCESS) {
                                    val executor = ContextCompat.getMainExecutor(this@MainActivity)
                                    val biometricPrompt = BiometricPrompt(this@MainActivity, executor,
                                        object : BiometricPrompt.AuthenticationCallback() {
                                            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                                                super.onAuthenticationSucceeded(result)
                                                isAuthenticated = true
                                            }
                                        })
                                    val promptInfo = BiometricPrompt.PromptInfo.Builder()
                                        .setTitle("StegaNic Vault Locked")
                                        .setSubtitle("Authenticate to access your secrets")
                                        .setAllowedAuthenticators(allowedAuth)
                                        .build()
                                    biometricPrompt.authenticate(promptInfo)
                                } else { isAuthenticated = true }
                            }) {
                                Text("Unlock Vault")
                            }
                        }
                    } else {
                        val navController = rememberNavController()
                        
                        NavHost(
                            navController = navController,
                            startDestination = if (isFirstLaunch) "onboarding" else "home"
                        ) {
                            composable("onboarding") {
                                OnboardingScreen(onFinish = {
                                    prefs.edit().putBoolean("is_first_launch", false).apply()
                                    navController.navigate("home") {
                                        popUpTo("onboarding") { inclusive = true }
                                    }
                                })
                            }
                            composable("home") {
                                // Lazy initialization of bulky ViewModel inside route mapping
                                val viewModel: SteganoViewModel = viewModel()
                                if (sharedUri != null) {
                                    LaunchedEffect(sharedUri) {
                                        viewModel.setEncodeImage(sharedUri)
                                        viewModel.setDecodeImage(sharedUri)
                                    }
                                }
                                HomeScreen(viewModel = viewModel, themeViewModel = themeViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}
