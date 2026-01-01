# تطبيق الرياضيات الفيدية - Vedic Math App

## نظرة عامة

هذا مشروع Android كامل يحول موقع HTML الخاص بالرياضيات الفيدية إلى تطبيق APK أصلي يمكن تثبيته على أجهزة Android. التطبيق يستخدم WebView لعرض المحتوى المحلي بطريقة سلسة وتجربة مستخدم تشبه التطبيقات الأصلية.

## هيكل المشروع

```
vedic_math_app/
├── app/
│   ├── build.gradle.kts        # ملف تكوين وحدة التطبيق
│   ├── proguard-rules.pro      # قواعد ProGuard للتحسين
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── assets/
│       │   │   └── index.html  # ملف HTML الرئيسي
│       │   ├── java/
│       │   │   └── com/
│       │   │       └── vedicmath/
│       │   │           └── app/
│       │   │               └── MainActivity.kt
│       │   └── res/
│       │       ├── layout/
│       │       │   └── activity_main.xml
│       │       ├── values/
│       │       │   ├── strings.xml
│       │       │   ├── colors.xml
│       │       │   └── themes.xml
│       │       ├── mipmap-anydpi-v26/
│       │       │   ├── ic_launcher.xml
│       │       │   └── ic_launcher_round.xml
│       │       └── drawable/
│       │           └── ic_launcher_foreground.xml
│       └── test/
│           └── java/
│               └── com/
│                   └── vedicmath/
│                       └── app/
├── build.gradle.kts            # ملف تكوين المشروع الرئيسي
├── settings.gradle.kts         # إعدادات المستودع
├── gradle.properties           # خصائص Gradle
├── local.properties            # مسارات SDK المحلية
├── gradlew                     # سكريبت التشغيل (Linux/Mac)
└── gradle/
    └── wrapper/
        ├── gradle-wrapper.jar
        └── gradle-wrapper.properties
```

## المتطلبات

لـبناء التطبيق على جهازك المحلي، ستحتاج إلى:

### المتطلبات الأساسية

1. **Java Development Kit (JDK) 17 أو أحدث**
   - يمكن تنزيله من: https://adoptium.net/
   - أو باستخدام Homebrew على Mac: `brew install openjdk@17`

2. **Android Studio (مُوصى به)**
   - تنزيل من: https://developer.android.com/studio
   - يتضمن جميع أدوات Android SDK المطلوبة

3. **Android SDK Command Line Tools** (إذا لم تستخدم Android Studio)
   - تنزيل من: https://developer.android.com/studio#command-line-tools-only

### تثبيت JDK على أنظمة مختلفة

**Windows:**
```
1. حمل JDK 17 من https://adoptium.net/
2. شغل ملف التثبيت واتبع التعليمات
3. أضف JAVA_HOME إلى متغيرات البيئة
4. أضف %JAVA_HOME%\bin إلى PATH
```

**macOS:**
```bash
# باستخدام Homebrew
brew install openjdk@17

# أو باستخدام SDKMAN
curl -s "https://get.sdkman.io" | bash
sdk install java 17.0.8-tem
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-17-jdk
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

## خطوات البناء

### الطريقة الأولى: باستخدام Android Studio

1. **استيراد المشروع**
   - افتح Android Studio
   - اختر "Open" وحدد مجلد `vedic_math_app`
   - انتظر حتى يتم تحميل المشروع بالكامل

2. **مزامنة Gradle**
   - ستظهر رسالة "Sync Project with Gradle Files"
   - انقر فوق "Sync Now" أو اختر من القائمة: `File > Sync Project with Gradle Files`

3. **بناء APK**
   - انتقل إلى: `Build > Build Bundle(s) / APK(s) > Build APK(s)`
   - انتظر حتى اكتمال البناء
   - ستجد ملف APK في: `app/build/outputs/apk/debug/app-debug.apk`

### الطريقة الثانية: باستخدام سطر الأوامر

1. **تأكد من تثبيت Java**
   ```bash
   java -version
   # يجب أن يظهر Java 17 أو أحدث
   ```

2. **تصدير متغيرات البيئة**
   ```bash
   export JAVA_HOME=/path/to/your/jdk-17
   export ANDROID_HOME=/path/to/android/sdk
   export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools
   ```

3. **بناء APK**
   ```bash
   cd vedic_math_app
   chmod +x gradlew
   ./gradlew assembleDebug
   ```

4. **ملف APK الناتج**
   - سيُوجد في: `app/build/outputs/apk/debug/app-debug.apk`

## تثبيت التطبيق على الجهاز

### الطريقة الأولى: باستخدام USB Debugging

1. **تفعيل خيارات المطور**
   - انتقل إلى الإعدادات > حول الهاتف
   - اضغط على "رقم البناء" 7 مرات
   - ارجع إلى الإعدادات > خيارات المطور
   - فعّل "USB Debugging"

2. **توصيل الجهاز**
   - استخدم كابل USB لتوصيل الهاتف بالكمبيوتر
   - وافق على طلب USB Debugging على الهاتف

3. **نقل APK وتثبيته**
   - انسخ ملف APK إلى الهاتف
   - اضغط على الملف واتبع تعليمات التثبيت

### الطريقة الثانية: باستخدام ADB

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## إعدادات SDK المطلوبة

تأكد من تثبيت المكونات التالية في Android SDK:

| المكون | الإصدار | ملاحظات |
|--------|---------|---------|
| Android SDK Build-Tools | 34.0.0 | أو أحدث |
| Android SDK Platform | 34 | Android 14 |
| Platform-Tools | أحدث | أدوات ADB |

### تثبيت SDK باستخدام sdkmanager

```bash
export ANDROID_HOME=/path/to/android/sdk

# عرض الحزم المتاحة
sdkmanager --list

# تثبيت المكونات المطلوبة
sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
```

## مميزات التطبيق

- ✅ تصميم متجاوب يعمل على جميع أحجام الشاشات
- ✅ دعم كامل للغة العربية (RTL)
- ✅ أزرار التنقل (رجوع، تحميل)
- ✅ دعم JavaScript للتفاعل
- ✅ تخزين محلي للبيانات
- ✅ مؤثرات بصرية وتحميل سلس
- ✅ أيقونة تطبيق مخصصة

## تخصيص التطبيق

### تغيير اسم التطبيق

عدّل ملف `app/src/main/res/values/strings.xml`:

```xml
<string name="app_name">اسم تطبيقك الجديد</string>
```

### تغيير أيقونة التطبيق

استبدل الملفات في `app/src/main/res/mipmap-*/` بأيقونات مخصصة بأحجام:
- mdpi: 48x48 بكسل
- hdpi: 72x72 بكسل
- xhdpi: 96x96 بكسل
- xxhdpi: 144x144 بكسل
- xxxhdpi: 192x192 بكسل

### تعديل ملف HTML

يمكنك تعديل ملف `app/src/main/assets/index.html` لتحديث محتوى التطبيق. أي تغييرات ستظهر في التطبيق بعد إعادة البناء.

## استكشاف المشاكل

### مشكلة: JAVA_HOME غير محددة

```
ERROR: JAVA_HOME is not set and no 'java' command could be found
```

**الحل:**
```bash
export JAVA_HOME=/path/to/jdk-17
```

### مشكلة: Android SDK غير موجود

```
ERROR: SDK location not found
```

**الحل:**
1. أنشئ ملف `local.properties` في مجلد المشروع
2. أضف السطر: `sdk.dir=/path/to/android/sdk`

### مشكلة: فشل في تحميل Tailwind CSS

التطبيق يتطلب اتصال إنترنت لتحميل مكتبات CSS الخارجية. تأكد من:
- وجود إذن INTERNET في AndroidManifest.xml
- اتصال الجهاز بالإنترنت عند第一次 التشغيل

### مشكلة: بطء البناء

لزيادة سرعة البناء:

1. **تفعيل الترجمة الموازية**
   ```bash
   ./gradlew assembleDebug --parallel
   ```

2. **استخدام daemon**
   ```bash
   ./gradlew assembleDebug --daemon
   ```

3. **زيادة ذاكرة Gradle**
   ```bash
   # في gradle.properties
   org.gradle.jvmargs=-Xmx4096m -XX:+HeapDumpOnOutOfMemoryError
   ```

## معلومات إضافية

### معلومات تقنية

- **لغة البرمجة:** Kotlin
- **الحد الأدنى لـ SDK:** Android 7.0 (API 24)
- **الهدف SDK:** Android 14 (API 34)
- **نظام البناء:** Gradle 8.0
- **مكتبة الواجهة:** Material Components

### الموارد المفيدة

- [توثيق Android Developer](https://developer.android.com/docs)
- [توثيق Kotlin](https://kotlinlang.org/docs/home.html)
- [دليل Gradle](https://docs.gradle.org/current/userguide/userguide.html)

## الترخيص

هذا المشروع مُتاح للاستخدام الشخصي والتجاري بدون قيود.

---

**تم إنشاء هذا المشروع تلقائياً بواسطة MiniMax Agent**
