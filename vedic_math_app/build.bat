@echo off
REM بناء تطبيق الرياضيات الفيدية - Windows Batch Script
REM =============================================

echo ============================================
echo   بناء تطبيق الرياضيات الفيدية
echo ============================================
echo.

REM التحقق من وجود Java
java -version >nul 2>&1
if errorlevel 1 (
    echo [خطأ] لم يتم العثور على Java!
    echo يرجى تثبيت JDK 17 من: https://adoptium.net/
    echo ثم إضافة JAVA_HOME إلى متغيرات البيئة.
    pause
    exit /b 1
)

echo [✓] تم التحقق من Java

REM التحقق من وجود Android SDK
if not exist "local.properties" (
    echo [خطأ] ملف local.properties غير موجود!
    echo يرجى إنشاء ملف local.properties مع مسار Android SDK
    echo مثال: sdk.dir=C:\Users\YourName\AppData\Local\Android\Sdk
    pause
    exit /b 1
)

echo [✓] تم التحقق من Android SDK

REM التحقق من وجود Gradle Wrapper
if not exist "gradle\wrapper\gradle-wrapper.jar" (
    echo [خطأ] ملف gradle-wrapper.jar غير موجود!
    echo يرجى تنزيله من مستودع Gradle
    pause
    exit /b 1
)

echo [✓] تم التحقق من Gradle Wrapper

echo.
echo جاري بناء التطبيق...
echo ============================================

REM بناء APK
gradlew.bat assembleDebug

if errorlevel 1 (
    echo.
    echo [خطأ] حدث خطأ أثناء البناء!
    echo راجع الرسائل أعلاه للمزيد من التفاصيل.
    pause
    exit /b 1
)

echo.
echo ============================================
echo [✓] تم بناء التطبيق بنجاح!
echo.
echo ملف APK موجود في:
echo app\build\outputs\apk\debug\app-debug.apk
echo.
echo لبناء نسخة_release (موقعة):
echo gradlew.bat assembleRelease
echo.
echo ============================================
pause
