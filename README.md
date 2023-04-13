TODOs:
1. The app doesn't play audio files. Need to add audio-player with supporting live-streaming
2. The app supports only 2 types of elements: audio and link. So, we need to check all possible types.
3. The app resues a model for elements and children (`RemoteRadioElement`). Need to check if it's the same model or not.
4. Need to cover codebase by Unit-tests and if we have enough time integration tests.
5. The app uses `compose`-library, so test the apk with staging environment (disabled debuggable mode) to reach the best performance of the lists. You can find APK <a href="https://github.com/freedroider/Take-Home-Challenge/blob/main/app-staging.apk">here</a>.
