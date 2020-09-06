### Pure Fabrication
`Fabricated class/ artificial class – assign set of related responsibilities 
that doesn't represent any domain object.`

For example, you have a `Customer` object, how do you implement saving `Customer` in the database?

So, following the `Pure Fabricatio`n principle, we will create a `CustomerService` or a repository that will retrieve and save such an object to the database.

Such a class is a fabrication of the imagination. 
Ideally, the responsibilities assigned to this fabrication support `High Cohesion` and `Low Coupling`,
so that the design of the fabrication is very clean.