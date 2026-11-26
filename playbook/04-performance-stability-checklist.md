# 04 · Mobile Performance & Stability Checklist

> Actionable engineering gates for Startup, Frame Pacing, Memory, Crash Governance, and Binary Footprint.

## 1. Cold Startup (`TTID` / `TTFD`)

- [ ] **Zero main-thread blocking I/O** in `Application.onCreate()`, `ContentProvider` auto-initializers, and first `Activity`/`ViewController` load.
- [ ] **Defer non-critical SDKs**: Analytics, crash extras, and ad/push SDKs that are not needed for frame 1 initialize lazily or after `reportFullyDrawn()`.
- [ ] **Baseline Profiles (Android)** & pre-warmed caches compiled and verified with Macrobenchmark.
- [ ] **Preferences migration**: Replace large synchronous `SharedPreferences` reads on startup with async `DataStore` or background preloading.

## 2. Rendering & Frame Pacing (60 / 120 Hz)

- [ ] **Zero network, DB, or large JSON decode (> 50 KB) on UI thread** (`Dispatchers.Main` / `@MainActor` / Flutter UI isolate).
- [ ] **List virtualization**: Stable `key` + `contentType` in Jetpack Compose `LazyColumn`, `Identifiable` IDs in SwiftUI `ForEach`, `ListView.builder` in Flutter.
- [ ] **Deferred state reads**: Animations and scroll offsets read state inside draw/layout modifiers (`graphicsLayer {}`, `offset {}`) rather than recomposing parent containers every frame.
- [ ] **Image decoding**: Downsample bitmaps to target view dimensions before decode; never load full 12 MP camera images raw into list cells.

## 3. Memory & Crash Governance

- [ ] **Leak-free lifecycles**: No `Activity`, `Fragment`, or `View` held by singletons, static fields, or uncancelled coroutines/listeners.
- [ ] **IPC / Binder safety**: Keep `Bundle` / `Intent` payloads well below 500 KB (avoid `TransactionTooLargeException`); pass IDs, not full serialized lists.
- [ ] **OEM / ROM defensive hygiene**: Any framework/ROM crash guard (e.g., targeted `Looper.loop()` allowlist) must match exact exception + message + OEM/API allowlist and emit non-fatal telemetry—never swallow app logic bugs silently.
- [ ] **Symbolicated release builds**: Automated upload of `mapping.txt`, native `.so` debug symbols, and `.dSYM` verified in CI/CD before rollout.

## 4. Binary & Network Efficiency

- [ ] **R8 Full Mode + Resource Shrinking** enabled in Android release builds; unused ABIs stripped via AAB / splits.
- [ ] **Assets compressed**: Convert large PNG/JPG bundle assets to WebP/AVIF or SVG/vector drawables.
- [ ] **HTTP/2 / QUIC & Connection Reuse**: Single shared HTTP client (`OkHttp` / `URLSession`) with proper cache headers and request deduplication.
