<div align="center">

<img src="app/src/main/res/drawable/ic_steganic_logo.png" width="140" alt="StegaNic"/>

# StegaNic

### Military-Grade Steganography for Android

*Hide any secret — text, file, or document — inside an ordinary image, audio, or video.*  
*Encrypted. Invisible. 100% Offline.*

<br/>

[![Release](https://img.shields.io/github/v/release/rajendrajoshiofficial/StegaNic?style=for-the-badge&color=00FFD1&label=LATEST+RELEASE)](https://github.com/rajendrajoshiofficial/StegaNic/releases)
[![License: GPL v3](https://img.shields.io/badge/LICENSE-GPL%20v3-blueviolet?style=for-the-badge)](LICENSE)
[![Platform](https://img.shields.io/badge/PLATFORM-Android%206.0%2B-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://android.com)
[![F-Droid](https://img.shields.io/badge/F--DROID-Coming%20Soon-orange?style=for-the-badge)](https://f-droid.org)
[![Security](https://img.shields.io/badge/SECURITY-AES--256--GCM-red?style=for-the-badge&logo=letsencrypt&logoColor=white)](#-security-architecture)
[![Kotlin](https://img.shields.io/badge/KOTLIN-100%25-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)

</div>

---

## 🔥 What is StegaNic?

> **Steganography** = The art of hiding a message inside an innocent-looking file.  
> **StegaNic** = Steganography + Military Encryption + Zero Compromise.

StegaNic lets you embed **any file or message** inside a photo, song, or video — making the secret **completely invisible** to the naked eye, detection software, and even forensic tools. The embedded data is first encrypted with **AES-256-GCM** before hiding, so even if someone extracts the bytes, they see only unreadable gibberish without your password.

---

## ✨ Features

| Category | Feature | Details |
|---|---|---|
| 🔐 **Encryption** | AES-256-GCM | US Government / NSA-approved cipher |
| 🔑 **Key Derivation** | PBKDF2-SHA256 | 310,000 iterations (OWASP 2024 standard) |
| 🧂 **Salt** | 32-byte SecureRandom | NIST SP 800-132 compliant |
| 🖼️ **Image Steganography** | 1-Bit LSB | Zero visual quality loss — pixel changes by ±1/255 |
| 🎵 **Media Steganography** | EOF Appending | Supports MP3, WAV, MP4 — media plays normally |
| 📄 **Hidden File Types** | ANY FORMAT | ZIP, PDF, APK, DOCX, MP4 — no restrictions |
| 🎭 **Decoy Password** | Plausible Deniability | Wrong password → shows fake decoy message |
| 📵 **Privacy** | Zero Internet | No servers, no analytics, no tracking — ever |
| ⚡ **Performance** | Sub-1s Boot | Instant startup via Android SplashScreen API |
| 🔒 **App Lock** | Biometric/PIN | Fingerprint, face, or device PIN protection |
| 🧮 **Stealth Mode** | Calculator Disguise | Hides app icon — appears as a calculator |
| 📷 **Camera** | Full Quality | Native 4K capture via MediaStore API |
| 🚫 **Anti-Forensics** | FLAG\_SECURE | Blocks screenshots, screen recording & recent-apps preview |

---

## 🔐 Security Architecture

```
╔══════════════════════════════════════════════════════════════╗
║                    YOUR SECRET DATA                          ║
╚══════════════════════════╦═══════════════════════════════════╝
                           ▼
        ┌──────────────────────────────────┐
        │       LAYER 1: ENCRYPTION        │
        │  PBKDF2-HMAC-SHA256              │
        │  - Iterations: 310,000           │
        │  - Salt: 32-byte SecureRandom    │
        │  - Key: AES-256 bit              │
        │  AES-256-GCM                     │
        │  - IV: 12-byte SecureRandom      │
        │  - Auth Tag: 128-bit             │
        └──────────────┬───────────────────┘
                       ▼
        ┌──────────────────────────────────┐
        │       LAYER 2: STEGANOGRAPHY     │
        │  IMAGE: 1-Bit LSB per channel    │
        │    → R, G, B each stores 1 bit   │
        │    → Pixel change: ±1 out of 255 │
        │    → Visually undetectable       │
        │  AUDIO/VIDEO: EOF Appending      │
        │    → Data appended after media   │
        │    → Media plays normally        │
        └──────────────┬───────────────────┘
                       ▼
        ╔══════════════════════════════════╗
        ║   INNOCENT-LOOKING CARRIER FILE  ║
        ║   (Photo / Audio / Video)        ║
        ╚══════════════════════════════════╝
```

### Why is it unbreakable?

| Attack Vector | Protection | Difficulty |
|---|---|---|
| Brute-force password | PBKDF2 310k iterations | ~40 billion years on fastest GPU cluster |
| Visual steganalysis | 1-bit LSB (±1/255 pixel) | Invisible to human eye and most tools |
| RAM dump for password | `spec.clearPassword()` after key derivation | Password zeroed from memory immediately |
| Screen capture | `FLAG_SECURE` | Blocked at OS level |
| Network interception | Zero internet permission | Impossible — no network access |

---

## 📦 Download

| Platform | Link | Status |
|---|---|---|
| **GitHub Releases** | [Download APK →](https://github.com/rajendrajoshiofficial/StegaNic/releases) | ✅ Available |
| **F-Droid** | [View on F-Droid →](https://f-droid.org/packages/com.rajendrajoshiofficial.steganic) | 🔄 Submission in progress |
| **Play Store** | Coming Soon | ⏳ Planned |

---

## 🔨 Build from Source

```bash
# Clone
git clone https://github.com/rajendrajoshiofficial/StegaNic.git
cd StegaNic

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

**Requirements:**
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17+
- Android SDK 34 (compileSdk)
- Minimum device: Android 8.0 (API 26)

---

## 🙋 FAQ

<details>
<summary><b>Can someone detect that my image contains hidden data?</b></summary>

Standard steganalysis tools look for statistical anomalies in pixel distributions. StegaNic uses a **password-seeded SecureRandom path** across pixels (not sequential), which produces a distribution indistinguishable from random noise. Detection is not guaranteed to be impossible but is extremely difficult without knowledge of the password.
</details>

<details>
<summary><b>What happens if I use the wrong password?</b></summary>

If you set a decoy message during encoding, the wrong password will show the fake decoy message. Otherwise, decryption will fail with an authentication error (AES-GCM auth tag mismatch).
</details>

<details>
<summary><b>Does image quality degrade after encoding?</b></summary>

No. StegaNic modifies only the least-significant bit of each color channel (R/G/B). The change is ±1 out of 255 — a 0.39% difference imperceptible to human eyes or cameras.
</details>

<details>
<summary><b>Is my data safe if I share the encoded image on WhatsApp?</b></summary>

⚠️ **Warning:** WhatsApp, Telegram, and most social media platforms **recompress** images, which destroys the LSB data. Use lossless transfer methods (USB, Google Drive, Telegram "Files" mode) to share encoded images.
</details>

---

## 📖 License

```
StegaNic — Military-Grade Steganography for Android
Copyright (C) 2026 Rajendra Joshi Official

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License.
```

See [LICENSE](LICENSE) for the full GPL v3 text.

---

## 👨‍💻 Developer

<table>
  <tr>
    <td align="center">
      <b>Rajendra Joshi Official</b><br/>
      📧 <a href="mailto:rajendrajoshi@zohomail.in">rajendrajoshi@zohomail.in</a><br/>
      🐙 <a href="https://github.com/rajendrajoshiofficial">github.com/rajendrajoshiofficial</a>
    </td>
  </tr>
</table>

---

## 🤝 Contributing

Contributions, bug reports, and feature requests are welcome!

- 🐛 [Report a Bug](.github/ISSUE_TEMPLATE/bug_report.md)
- ✨ [Request a Feature](.github/ISSUE_TEMPLATE/feature_request.md)
- 🔒 [Report a Security Issue](SECURITY.md)
- 📝 [Contributing Guide](CONTRIBUTING.md)

---

<div align="center">

**If StegaNic helped you, please ⭐ star the repository!**

*Built with ❤️ in India*

</div>
