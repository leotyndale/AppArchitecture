# 05 · Mobile Architecture Decision Record (ADR) Template

> Copy this template into `docs/adr/XXXX-short-title.md` whenever introducing a new cross-platform framework, module boundary, local persistence engine, or on-device AI runtime.

---

# ADR-XXXX: [Short Descriptive Title]

- **Status**: `Proposed` | `Accepted` | `Deprecated` | `Superseded by ADR-YYYY`
- **Date**: `YYYY-MM-DD`
- **Owners**: `@handle1`, `@handle2`
- **Platforms Affected**: `Android` | `iOS` | `Flutter` | `KMP`

## 1. Context & Problem Statement

Describe the concrete engineering or product problem (e.g., cold start latency regression, cross-module circular dependency, duplicated sync logic across Android and iOS) and measurable constraints (min OS version, target RAM tier, binary size budget).

## 2. Decision Drivers

- Driver 1 (e.g., must support 4 GB RAM Android devices without OOM)
- Driver 2 (e.g., must preserve incremental build time under 20s)
- Driver 3 (e.g., must work offline and migrate cleanly from vN-1 schema)

## 3. Considered Options

1. **Option A**: ...
2. **Option B**: ...
3. **Option C (Status Quo / Do Nothing)**: ...

## 4. Decision & Trade-offs

State the chosen option clearly and explain **why** its pros outweigh its cons relative to the drivers above.

## 5. Impact Assessment

| Dimension | Expected Impact / Budget | Mitigation / Owner |
| --- | --- | --- |
| **Binary Size (APK / IPA)** | e.g., +1.8 MB | Strip unused ABIs / dynamic delivery |
| **Cold Start / Memory** | e.g., +0 ms TTID (lazy init) | Verify via Macrobenchmark / MetricKit |
| **Backward Compatibility** | e.g., Room v12 -> v13 migration | Migration unit test + staged rollout |
| **Security & Privacy** | e.g., Keystore / Keychain token | Security review checklist passed |

## 6. Verification & Rollback Plan

- **Automated Tests / Fitness Rules**: What CI test or Konsist/ArchUnit rule enforces this decision?
- **Rollout & Kill-Switch**: How is this gated during `1% -> 5% -> 20% -> 100%` rollout, and how do we revert safely if crash/ANR rate spikes?
