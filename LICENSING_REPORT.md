# Oracle JDK to Amazon Corretto Migration — Licensing Report

## 1. Summary

- **Project:** GoYelp (rosseneri:GoYelp 0.0.1-SNAPSHOT)
- **Files scanned:** all tracked files excluding `target/`, `.git/`, `node_modules/` — 10 Java sources, 1 `pom.xml`, 2 Dockerfiles, 3 shell scripts, 1 Kubernetes manifest
- **Issues found:** 1 (non-Corretto JDK installed from the retired AdoptOpenJDK apt repository)
- **Issues fixed:** 1
- **Manual review items:** 0

Zero Oracle internal API imports were detected in the Java sources — a valid outcome. No CI workflow files and no `.devcontainer/devcontainer.json` exist in this project (recorded N/A).

## 2. Oracle API Dependencies

| File | Line | Oracle API | Replacement | Status |
|---|---|---|---|---|
| N/A | — | No Oracle API imports detected | — | Pass |

## 3. Licensing Cost Estimate

Oracle Java SE Universal Subscription (per-employee pricing):

| Employees | Rate | Monthly | Annual | 5-Year |
|---|---|---|---|---|
| 500 | $15.00/emp/mo | $7,500 | $90,000 | $450,000 |
| 1,000 | $12.00/emp/mo | $12,000 | $144,000 | $720,000 |
| 5,000 | $10.50/emp/mo | $52,500 | $630,000 | $3,150,000 |
| 10,000 | $10.50/emp/mo* | $105,000 | $1,260,000 | $6,300,000 |

\* The 10,000-employee rate is extrapolated from the highest published tier (3,000–9,999 employees). Actual enterprise pricing is negotiated with Oracle.

**Amazon Corretto cost: $0** — no licensing fees, no per-employee subscription, long-term support at no charge.

## 4. Modified Files

| File | Change |
|---|---|
| `build/base/Dockerfile` | Replaced the AdoptOpenJDK apt repository and `adoptopenjdk-8-hotspot` package with the Amazon Corretto apt repository and `java-1.8.0-amazon-corretto-jdk`. The key is now installed as a signed keyring (`/usr/share/keyrings/corretto-keyring.gpg`) instead of the deprecated `apt-key add`. Base image remains `node:16` (Debian), so `apt-get` is retained — no apt-to-yum conversion. |

Java major version preserved (8), matching `<java.version>1.8</java.version>` in `pom.xml`. No Java source, `pom.xml`, or business logic was modified.

## 5. Manual Review Items

None.

## 6. Annotation-Processor Upgrades

None required. The project declares no Lombok or MapStruct dependency, so the Step 4c annotation-processor compatibility procedure does not apply.

## 7. Validation

- Build tool detected: Maven only (`pom.xml`, no Gradle build files)
- Command: `mvn clean compile -DskipTests` — **PASS** (exit code 0) on Amazon Corretto 25.0.5
- `.bak` sweep: **PASS** (no backup files present)
