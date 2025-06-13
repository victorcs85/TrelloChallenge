-ignorewarnings
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations
-keepattributes EnclosingMethod

# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# Model
-keep class **.domain.model.** { *; }
-keep class **.data.dto.** { *; }

# Gms
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# General enuns to avoid crashes in deserializes
-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# AndroidX
-keep class com.google.android.material.** { *; }

-keep class * extends androidx.fragment.app.Fragment{}

-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**

-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

-keepclassmembers class **.R$* {
    public static <fields>;
}
