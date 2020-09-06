### Protected Variations
`Identify points of predicted variation and create a stable interface around them`

In fact, `Protected Variations` it's the same as `Open/Closed Principle` from SOLID.
They are two expressions of the same principle

Protected Variations or implementation hiding, or otherwise protected changes. 
How to design objects so that changing one object do not affect others? 
How to avoid a situation when changing the code of one object will have to make changes to many other objects in the system?

It seems we have already seen something similar. Yes,`Low Coupling` or `Dependency Injection`. 
Exactly! But the essence of the current principle is a little different. 
The point is to identify the "change points"(points of predicted variation) and fix them in the abstraction (interface). 
"Points of change" are nothing more than our objects that can change.

That is, the essence of this principle is to determine the places in the system 
where the behavior can change and highlight the abstraction, 
on the basis of which further programming will take place using this object.

All this is being done only to ensure the stability of the interface. 
If there are many changes associated with objects, in this case, it is considered unstable, 
and then you need to move it into an abstraction, on which it will depend, 
or else to distribute duties and responsibilities in the code in a different way