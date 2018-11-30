package com.eight.server.WebSocket;

public enum SessionState {
    Initializing,
    LogingIn,
    SecurityCheck,
    LoginSucceed,
    Online,
    Offline,
}
