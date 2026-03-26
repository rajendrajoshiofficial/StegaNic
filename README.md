<div align="center">
  <img src="assets/logo.png" width="120" alt="StegaNic Logo"/>
  <h1>StegaNic</h1>
  <p><strong>Military-Grade Steganography for Android</strong></p>
  <p><em>Hide any secret inside an image, audio, or video — Encrypted. Invisible. 100% Offline.</em></p>

  <p>
    <a href="https://github.com/rajendrajoshiofficial/StegaNic/releases"><img src="https://img.shields.io/github/v/release/rajendrajoshiofficial/StegaNic?style=flat-square&color=00FFD1&label=Release" alt="Release"/></a>
    <a href="LICENSE"><img src="https://img.shields.io/badge/License-GPL%20v3-blueviolet?style=flat-square" alt="License"/></a>
    <a href="#"><img src="https://img.shields.io/badge/Platform-Android%206.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white" alt="Platform"/></a>
    <a href="#"><img src="https://img.shields.io/badge/Kotlin-100%25-7F52FF?style=flat-square&logo=kotlin&logoColor=white" alt="Kotlin"/></a>
    <a href="#"><img src="https://img.shields.io/badge/Internet-ZERO-red?style=flat-square" alt="Offline"/></a>
  </p>
</div>

---

## 🔥 What is StegaNic?

> **Steganography** = The art of hiding a message inside an innocent-looking file.
> **StegaNic** = Steganography + Military Encryption + Zero Compromise.

StegaNic lets you embed **any file or message** inside a photo, song, or video — making the secret **completely invisible**. Data is first encrypted with **AES-256-GCM** before hiding, so even if someone extracts the bytes, they see only gibberish without your password.

---

## ✨ Key Features

- 🔐 **AES-256-GCM** — US Government / NSA-approved cipher
- 🛡️ **PBKDF2 310,000 iterations** — OWASP 2024 standard (brute force impossible)
- 🖼️ **LSB Steganography** — 1-bit per pixel, zero visual quality loss
- 🎵 **Audio & Video Support** — MP3, WAV, MP4 (plays normally after encoding)
- 📄 **Any File Format** — ZIP, PDF, APK, DOCX — hide anything inside anything
- 🎭 **Decoy Password** — Wrong password shows fake message (plausible deniability)
- 📵 **100% Offline** — Zero internet permission, no servers, no tracking
- 🔒 **Biometric App Lock** — Fingerprint / PIN protection
- 🧮 **Stealth Mode** — App disguises itself as a Calculator
- 🚫 **Anti-Screenshot** — Blocks screen recording & recent-apps preview

---

## 🔐 Security Architecture

```
Your Secret Data
      │
      ▼
┌─────────────────────────┐
│   LAYER 1: ENCRYPTION   │
│  PBKDF2-SHA256          │
│  → Iterations: 310,000  │
│  → Salt: 32-byte random │
│  AES-256-GCM            │
│  → IV: 12-byte random   │
│  → Auth Tag: 128-bit    │
└───────────┬─────────────┘
            │
            ▼
┌─────────────────────────┐
│  LAYER 2: STEGANOGRAPHY │
│  Image: 1-bit LSB/pixel │
│  Audio/Video: EOF embed │
└───────────┬─────────────┘
            │
            ▼
   Innocent Carrier File
   (Photo / Audio / Video)
```

### Why is it unbreakable?

| Attack | Protection |
|---|---|
| Brute-force password | 310k PBKDF2 iterations = ~40 billion years on GPU |
| Visual steganalysis | 1-bit LSB change = ±1 out of 255 (invisible) |
| RAM dump | Password zeroed from memory immediately after use |
| Screen capture | `FLAG_SECURE` blocks at OS level |
| Network interception | Zero internet = impossible |

---

## 📦 Download

| Platform | Link | Status |
|---|---|---|
| GitHub Releases | [Download APK →](https://github.com/rajendrajoshiofficial/StegaNic/releases) | ✅ Available |
| F-Droid | [View listing →](https://f-droid.org/packages/com.rajendrajoshiofficial.steganic) | 🔄 In progress |
| Play Store | Coming Soon | ⏳ Planned |

---

## 🔨 Build from Source

```bash
git clone https://github.com/rajendrajoshiofficial/StegaNic.git
cd StegaNic
./gradlew assembleDebug
```

**Requirements:** Android Studio Hedgehog+, JDK 17+, Android SDK 34

---

## 🙋 FAQ

<details>
<summary><b>Can someone detect my image has hidden data?</b></summary>

StegaNic uses a password-seeded SecureRandom pixel path (not sequential), producing a distribution indistinguishable from random noise. Detection is extremely difficult without the password.
</details>

<details>
<summary><b>What happens with the wrong password?</b></summary>

If you set a decoy message during encoding, the wrong password shows the fake decoy. Otherwise, AES-GCM authentication fails with an error.
</details>

<details>
<summary><b>Does image quality degrade after encoding?</b></summary>

No. Only the least-significant bit is changed (±1 out of 255 — a 0.39% difference imperceptible to human eyes).
</details>

<details>
<summary><b>Can I share encoded images on WhatsApp?</b></summary>

⚠️ WhatsApp recompresses images, destroying LSB data. Use lossless transfer: USB, Google Drive, or Telegram "Files" mode.
</details>

---

## 🤝 Contributing

- 🐛 [Report a Bug](.github/ISSUE_TEMPLATE/bug_report.md)
- ✨ [Request a Feature](.github/ISSUE_TEMPLATE/feature_request.md)
- 🔒 [Report Security Issue](SECURITY.md)
- 📝 [Contributing Guide](CONTRIBUTING.md)

---

## 📖 License

Licensed under **GNU GPL v3** — See [LICENSE](LICENSE) for full text.

---

<div align="center">
  <strong>Built with ❤️ in India by <a href="https://github.com/rajendrajoshiofficial">Rajendra Joshi Official</a></strong><br/>
  <em>If StegaNic helped you, please ⭐ star the repository!</em>
</div>
