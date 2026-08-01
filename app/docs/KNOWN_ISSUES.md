# Known Issues

## Android Studio Quail + Built-in Kotlin + KSP

Issue:

```
Using kotlin.sourceSets DSL to add Kotlin sources is not allowed with built-in Kotlin.
```

Temporary Fix:

```properties
android.disallowKotlinSourceSets=false
```

Reason:

Current KSP versions are still catching up with Android Studio Quail (2026) and Built-in Kotlin.

Remove this property once KSP officially supports the new toolchain.