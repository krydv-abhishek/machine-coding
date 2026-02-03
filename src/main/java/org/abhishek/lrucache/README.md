

**No equals/hashCode overridden.**

Now:

`User u1 = new User("Abhishek"); 
User u2 = new User("Abhishek");`

`cache.put(u1, "Engineer");`

Now try:
`cache.get(u2);`

What happens?
It returns null.

##### Why?

Because:
u1 != u2 (different memory)
equals() returns false
hashCode() different
Even though logically they represent the same user.

##### Why This Is Dangerous

If your cache key is meant to represent logical identity
(e.g., userId, request object, query params),
Then without overriding:
Your cache becomes useless for value-based lookup.
You’ll only retrieve values if you pass the exact same object reference.


* Do you understand HashMap internals? <br>
* Do you understand object identity vs logical equality? <br>
* Do you understand value objects?

**Strong answer:**
* “If we don’t override equals and hashCode, the cache will use reference equality. 
Two logically equal objects won’t match unless they are the same instance, 
which is usually incorrect for a cache.”

##### hashCode()
* Not random
* Not recalculated on every state change
* Typically derived from object identity (often memory-related)
* Stable during the object’s lifetime

Even if you mutate fields, default hashCode() does not change.

##### Why Equals Alone Is Not Enough
* HashMap lookup algorithm:
* Compute hash
* Find bucket
* Inside bucket → compare using equals
* If hash is different: You never reach equals comparison.
So overriding equals without hashCode is broken.

### If our LRU cache depends on:
`HashMap<K, Node>` <br>
And hash distribution is bad:<br>
get → no longer O(1) <br>
put → no longer O(1) <br>
So our LRU design assumption breaks. 