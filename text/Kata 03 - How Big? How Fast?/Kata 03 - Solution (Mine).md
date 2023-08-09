## How Big?

### #1 - Binary Digits
For the first ones that are just simply the unsigned integers, this is pretty easy.  Think, roughly, 1000 = 2^10.  Then X * 1000 = X * 2^10.  The exponent will be the number of bits.
- 1,000 -> 10 bits
- 1,000,000 -> 20 bits
- 1,000,000,000 -> 30 bits
- 1,000,000,000,000 -> 40 bits
- 8,000,000,000,000 -> 43 bits (2^3 * 2^40)

### #2 - Space to store town info
For brevity here we'll make the following upper bound assumptions for size
- Phone number -> 10 digits
- Name -> 20 letters (might be a bit much - but allows for a long first or last name)
- Address -> 50 letters
	- Here is where some additional info would be helpful.  If we are only storing info for this specific town then we really only need to worry about the street name and house number.  Otherwise, we would need state/province and country

Altogether this comes to 80 characters per entry.  For additional buffer, lets round up to 156 characters per entry.  Each entry will then be 128 bytes, or 2^7 bytes

To store 20,000 residences this would require 5,560,000 bytes -> approx 5.5 megabytes

### #3 - Nodes in a Binary Tree
First of all, 1,000,000 integers in a binary tree will mean 1,000,000 nodes

We can expect log2(1,000,000) nodes levels -> we can expect 20 levels

Each node will have value, left pointer, right pointer.  The architecture is 32-bit, so each pointer will be 4bytes and each value will be 4bytes.  Each node is then 12 bytes.  The whole tree will take approx 12 mega bytes to store.


## How Fast

### #2 - Binary Search

4.5ms for 10k entry search
6ms for 100k entry search

-> 33% latency increase for each 10x in entry size

10M entry -> 6ms * (1.33 ^ 2) = 10.667ms

### #3
16 characters long, 96 possible characters, 1mS per hash

Domain size is 96^16

To search the entire domain would take 96^16 ms, which is a very long time.  This would not be a feasible approach.


Came back with a calculator - this equates to 6.02e+23 days.

