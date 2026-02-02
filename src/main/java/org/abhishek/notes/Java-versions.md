### Java 8 – Streams & Functional Programming (Foundation Shift)
This changed how Java is written.
* What it introduced:
* Stream API
* lambda expressions
* functional interfaces
* Optional
* CompletableFuture
* New Date/Time API (java.time)

**Why it matters:**
* Functional style processing
* Parallel streams
* Cleaner concurrency (CompletableFuture)
* Immutable date handling

### Java 9 – Modules (JPMS)
**What:**
* module-info.java
* Strong encapsulation
* JDK modularized

**Why:**
* Better dependency boundaries
* Smaller runtime images (jlink)
* Stronger access control than public


### Java 10 – var
**What:**
* var <br> 

**Why:**
* Cleaner code
* Encourages readable APIs
<br>
**Important:**
  * Works only for local variables
  * Type still static (not dynamic)


### Java 11 – LTS + Big Removals
**What:**
* HttpClient (modern HTTP API)
* String methods: isBlank(), lines(), strip()
* Files.readString()
* Removed Java EE modules

**Why:**
* Real production adoption started here
* Modern HTTP usage


### Java 14-17
**What:**

* Records
  * What:
    * Immutable data carriers
    *   Auto equals/hashCode/toString
         
  * Why important:
     * Replaces boilerplate DTOs
     * Perfect for domain modeling
     * Used heavily with pattern matching

* Pattern Matching (instanceof + switch)
  * What:
    * Now `if (obj instanceof String s) { 
      System.out.println(s.length());
      }`
    * Earlier `if (obj instanceof String)  
      { String s = (String) obj;
      System.out.println(s.length());
      }`
    * `return switch(day) {
      case MONDAY -> {
        System.out.println("Start of week");
        yield 1;  // use yield to return value from block
      }      
      default -> 0;
      };`

  * Why important:
    * Cleaner polymorphism handling
    * More functional style
    * Foundation for sealed types

* Sealed Classes (Java 17)
  * What:
    * `public sealed class Shape
      permits Circle, Rectangle {}
      `

  * Why important:
    * Restricts inheritance
    * Helps model finite hierarchies
    * Powerful with pattern matching

* Text Blocks (""" """)
  * What:
    * `String json = """
      {
      "name": "Abhishek"
      }
      """;
      `

  * Why important:
    * SQL / JSON readability
    * Cleaner test data

**Why:**
* Real production adoption started here
* Modern HTTP usage


### Java 21 LTS
* Virtual threads
**What:**
* `Thread.startVirtualThread(() -> {
  // lightweight thread
  });
  `
* Millions of lightweight threads
* Structured concurrency

**Why:**
* Thread-per-request model becomes viable
* Simplifies async code
* Potentially reduces need for reactive frameworks

* Structured Concurrency
**What:**
* `try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
  ...
  }
  `
*  Manage multiple concurrent tasks as one unit
*  Cancels sibling tasks on failure
*  Cleaner than manual Future handling

**Why:**
* Thread-per-request model becomes viable
* Simplifies async code
* Potentially reduces need for reactive frameworks