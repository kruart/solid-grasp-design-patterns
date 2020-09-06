### Indirection
`Assign the responsibility to an intermediate object to mediate between other components or services, so that they are not directly coupled`

The essence of this principle can be found in many patterns, for example:
- bridge
- facade
- adapter
- mediator
- MVC


For example, let's take `MVC` pattern. 
UI logic doesn't really need a controller, but a model, domain logic. 
But we don't want that the UI logic is strongly related to the model, 
and perhaps in the UI we want to receive data and work with different logic. 
And it would be silly to connect the UI layer with business logic 
because we will get the code that will be difficult to change and maintain. 
Solution - we introduce `Controller` as an intermediary between `View` and `Model`. 
In this example, `Controller` is the application of the `Indirection` principle.