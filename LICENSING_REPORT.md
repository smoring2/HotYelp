# Oracle JDK → Amazon Corretto Licensing Report

## 1. Summary

- **Project:** GoYelp (`rosseneri:GoYelp:0.0.1-SNAPSHOT`)
- **Files scanned:** all `.java`, `pom.xml`, Dockerfiles, `.sh`, `.yaml` files (excluding `target/`, `build` output, `.git/`, `node_modules/`)
- **Oracle internal API imports found:** 0
- **Issues found:** 1 (non-Corretto JDK distribution installed in container base image)
- **Issues fixed:** 1
- **Manual review items:** 0

Zero Oracle API imports is a valid outcome; the Java sources use only standard
Java and Spring Framework APIs and were not modified. The project's Java version
(`<java.version>1.8</java.version>`) was left unchanged.

## 2. Dependency Table

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

\* The 10,000-employee rate is extrapolated from the highest published tier
(3,000–9,999 employees). Actual enterprise pricing is negotiated with Oracle.

**Amazon Corretto cost: $0** — no licensing fees, no-cost long-term support.

## 4. Modified Files

| File | Change |
|---|---|
| `build/base/Dockerfile` | JDK install switched from AdoptOpenJDK APT repo (`adoptopenjdk-8-hotspot`) to the Amazon Corretto APT repo (`java-1.8.0-amazon-corretto-jdk`). Java major version 8 preserved; `node:16` base image retained (Debian, so `apt-get` usage retained). |

Items verified as out of scope / unchanged:
- `src/main/java/**` — no `sun.*`, `com.sun.*`, or `com.oracle.*` imports.
- `pom.xml` — no Oracle vendor refs, no `com.oracle.*` deps, no Lombok dependency (Step 4c no-op).
- `build/hotyelp/Dockerfile` — base image is the project's own `base` image; no vendor JDK reference.
- `build/*/build.sh`, `build/hotyelp/bootstrap.sh` — no commercial JVM flags, no Oracle JDK paths.
- `deployment/deployment.yaml` — no JDK vendor references.
- No `.github/workflows/` or `.devcontainer/` present (N/A).

## 5. Manual Review Items

None. No Oracle-specific imports or unmapped vendor APIs were encountered.

## 6. Annotation-Processor Upgrades

None required. The project declares no Lombok or MapStruct dependency, so no
annotation-processor or `maven-compiler-plugin` changes were made.
