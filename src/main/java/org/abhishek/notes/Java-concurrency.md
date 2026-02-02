| Feature / Approach   | CompletableFuture    | Virtual Threads                | Reactive (Flux/Mono)               |
| -------------------- | -------------------- | ------------------------------ | ---------------------------------- |
| Blocking?            | Optional (can block) | Can block freely (cheap)       | Non-blocking only                  |
| Threads used         | OS threads (heavy)   | Virtual threads (light)        | Event-loop threads                 |
| API style            | Functional chaining  | Imperative synchronous         | Functional reactive                |
| Learning curve       | Moderate             | Low/medium                     | High                               |
| Use case             | Parallel async tasks | Legacy sync + high concurrency | Event streaming / high-scale async |
| Debugging            | Easy to moderate     | Easy                           | Hard                               |
| Backpressure support | No                   | No                             | Yes                                |
| Library dependencies | Java SE              | Java SE                        | External (Reactor, RxJava)         |



### The Core Idea of Virtual Threads
Virtual Threads are:

User-mode threads scheduled by the JVM instead of the OS.
Key difference:
| Platform Thread    | Virtual Thread      |
| ------------------ | ------------------- |
| 1:1 with OS thread | Many-to-few mapping |
| Heavy              | Lightweight         |
| OS scheduled       | JVM scheduled       |


### What Actually Happens Internally?
Virtual threads are built on continuation. A paused computation that can be resumed later.

When a virtual thread blocks (e.g., I/O):
JVM captures the execution state (stack)
Detaches it from OS thread
Frees OS thread for other work
Resumes virtual thread when I/O completes

This is called:
Mounting & Unmounting
Mounted → virtual thread attached to OS thread
Unmounted → virtual thread suspended, OS thread free
This is the core scalability trick.
