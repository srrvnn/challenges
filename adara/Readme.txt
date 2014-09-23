Files: 
--------------------

AdCallProcessorTest 20140515-190012.xml // result of the junit test file
saravananganesh_adara.zip // zip of all files in the project

--- src // contains all source classes
--- test // contains the junit class


Outlook:
--------------------

Everytime a choose ad request comes with a set of eligible ads,
	it choose an ad complying with the weights.

The weights have to be changed at the end of each choice
	to ensure all ads gets chosen eventually.
This has to be essentially done at the database level
	to ensure all systems comply with the new weights.

A stub simulates all interaction with the database in this case.


Algorithm:
--------------------

store total weight for each placementId during entry of PlacementAdMapping.

for each request

	compute a random number within total weight

	keep adding the weights of ads until the weight exceeds
		the random number

	choose this ad


Analysis:
--------------------

This algorithm runs in O(n) worst case to choose one ad.

This can be reduced to O(log n) by performing binary search, but that involves the storage
	of progressive weights taking up 0(n) space that has to be computed after every update.

This can be reduced to O(1) by creating an array with ads populated by weight. Choose one element at random.
	to O(1) time and O(n + m) space where n is the number of elements and m is highest weight.

The last two methods will be more efficiently if we do sampling with replacement, i.e. do not reduce the weight after an ad is chosen. that also means we can never be 100% sure of the frequency of the choosing. 