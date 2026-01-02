# 🚀 HTML to APK Builder

Professional tool to convert your HTML/CSS/JS projects into native Android apps with just a push to GitHub!

## ✨ Features

- **One-Click Build**: Simply push your code and get an APK automatically
- **Professional Results**: Native Android WebView app
- **Easy Configuration**: Just edit `app-config.json` to customize
- **GitHub Actions CI/CD**: Fully automated build pipeline
- **No Android Studio Required**: Build everything from your browser

## 📦 Quick Start

### 1. Configure Your App

Edit `app-config.json`:

```json
{
  "appName": "My Awesome App",
  "appId": "com.example.myapp",
  "versionCode": 1,
  "versionName": "1.0.0"
}
```

### 2. Add Your Content

Put your HTML files in `public/`:

```
public/
├── index.html
├── styles.css
└── assets/
    └── images/
```

### 3. Push to GitHub

```bash
git add .
git commit -m "My new app"
git push origin main
```

### 4. Get Your APK

1. Go to **Actions** tab
2. Wait for build (2-5 minutes)
3. Download APK from **Artifacts**

## 📁 Project Structure

```
html-to-apk-builder/
├── .github/workflows/build.yml  # CI/CD pipeline
├── android/                      # Android project
│   └── app/src/main/
│       ├── assets/www/          # Put HTML here!
│       ├── java/com.example/
│       └── res/
├── public/                       # Your HTML files here!
├── app-config.json              # Edit this!
├── README.md
└── .gitignore
```

## 🔧 Configuration

| Option | Description | Example |
|--------|-------------|---------|
| `appName` | Display name | "My App" |
| `appId` | Package ID | "com.example.myapp" |
| `versionCode` | Build number | 1 |
| `versionName` | Version | "1.0.0" |

## 📱 Installation

1. Enable "Install from unknown sources"
2. Transfer APK to device
3. Open and install

## 🔐 Notes

- Minimum Android: 5.0 (API 21)
- Target Android: 14 (API 34)
- WebView-based application
- Internet permission included

## 🤝 License

MIT - Use freely!
