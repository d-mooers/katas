The problem statement said that the author would post their answers, but I am unable to find them.  Luckily, some brave commenters shared their solutions in the comments so I will use those to review and reflect my answers.

## How Big?

### #1 - Binary Digits
Everyone in the comments was in agreement with my answers.  This was the most straightforward, so this makes the most sense.

### #2 - Storing residence info for a town
Here it seems like the lack of details led to some pretty different responses.  A few commenters speculated on the number of streets in the town, the number of houses on every street, and opted for a relational model where they only stored each street name once.  

I do think that this approach has its merit, and if this question was being used directly for a production representation of 20,000 residences then it would be more fitting.

However, I think in the spirit of approximation it is always safer to be on the upper bound.  With my solution, we observed potentially the `maximum` space to store the required information, without diving into additional details and spending time analyzing requirements.  Given that the purpose of the exercise was to *quickly* approximate these values, I still feel good about my answers.

### #3 - Binary Tree
This was another straightforward question.  My answers were similar to other commenters, except one, who simply converter their final answer to bytes without dividing by 8.

## How Fast?

### #1 - Baud Modem Line
This question I did not attempt as I was not sure the size of a page.  None of the comments even addressed this question unfortunately :(

### #2 - Binary Search
My answer for this one was a bit *too* much of an approximation.  Nobody commented on this question either, but looking back I should have just used the growth factor as log2(n) since it is binary search.  I wanted to do this without a calculator on the first run-through, so the approach I took initially is where the idea to use the percentage increase in latency came from.

I think my answer can still serve as a general approximation, but its not very good.

### #3 - Password Cracking
My answer was in agreement overall with the comments.