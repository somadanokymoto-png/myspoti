# Myspoti Custom — development checkpoint

This fork is derived from [kota-zo/myspoti](https://github.com/kota-zo/myspoti) and retains the upstream GPL-3.0 licensing and attribution.

## In the `dev/amoled-login-v1` branch

- Themed default colors for a midnight/AMOLED violet aesthetic, preserving existing user color choices.
- Login error recovery extended to recognized third-party identity providers.
- Retry of sign-in without immediately clearing cookies and Web storage; separate explicit option to clear session data.
- Brazilian Portuguese, European Portuguese and English labels for the session-reset action.
- Debug APK application ID distinct from upstream: `app.myspoti.customdev`, with app label `Myspoti Custom`.
- GitHub Actions configured to run for this development branch.

## Quality gate

These changes have **not** yet been verified by a successful Android build or real Spotify login test. The glassmorphism screen layouts, bespoke player redesign, and animations are still pending.

Build instructions: `cd base && ./gradlew :app:testDebugUnitTest :host-android:testDebugUnitTest :app:assembleDebug`.

This commit also triggers the Android CI workflow after GitHub Actions was enabled on the fork.
