# Security Policy

## Supported Versions

| Version | Supported |
|---------|-----------|
| 1.x     | ✅ Yes    |

## Reporting a Vulnerability

**Please do NOT report security vulnerabilities via public GitHub Issues.**

To responsibly disclose a vulnerability, email the developer directly:

📧 **rajendrajoshi@zohomail.in**  
Subject line: `[SECURITY] StegaNic Vulnerability Report`

Include:
- Description of the vulnerability
- Steps to reproduce
- Potential impact
- Your suggested fix (optional)

You will receive an acknowledgment within **72 hours**.
All valid reports will be credited in the next release changelog.

## Security Architecture

StegaNic uses:
- **AES-256-GCM** for encryption
- **PBKDF2-HMAC-SHA256** (250,000 iterations) for key derivation
- **1-Bit LSB Steganography** for lossless image embedding
- **EOF Appending** for audio/video carrier files
- **Zero internet permissions** — fully offline, no data collection
