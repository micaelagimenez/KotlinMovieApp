package com.app.movieapp.model

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    val success: Boolean,
    @SerializedName("guest_session_id") val sessionId: String,
    @SerializedName("expires_at") val expiresAt: String
)